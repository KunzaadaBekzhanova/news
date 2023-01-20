package megalab.news.services;


import lombok.RequiredArgsConstructor;
import megalab.news.dtos.Response;
import megalab.news.dtos.requests.NewsRequest;
import megalab.news.dtos.responses.CustomPage;
import megalab.news.dtos.responses.FullNewsResponse;
import megalab.news.dtos.responses.MainNewsResponse;
import megalab.news.dtos.responses.NewsResponse;
import megalab.news.exceptions.NotFoundException;
import megalab.news.models.News;
import megalab.news.models.User;
import megalab.news.repositories.CategoryRepo;
import megalab.news.repositories.CustomNewsRepo;
import megalab.news.repositories.NewsRepo;
import megalab.news.repositories.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final UserRepo userRepo;
    private final NewsRepo newsRepo;
    private final CategoryRepo categoryRepo;
    private final CustomNewsRepo customNewsRepo;

    public NewsResponse save(Authentication auth, NewsRequest newsRequest) {
        User user = userRepo.getByNickname(auth.getName());
        News news = convert(user, newsRequest);
        user.addNews(news);
        return new NewsResponse(newsRepo.save(news));
    }

    private News convert(User user, NewsRequest newsRequest) {
        return News.builder()
                .writer(user)
                .newsCover(newsRequest.newsCover())
                .heading(newsRequest.heading())
                .shortDescription(newsRequest.shortDescription())
                .newsContent(newsRequest.newsContent())
                .categories(categoryRepo.findAllById(newsRequest.categories()))
                .date(LocalDate.now())
                .build();
    }

    public FullNewsResponse findById(Long newsId) {
        News news = findOrElseThrow(newsId);
        // skipping news comments
        return new FullNewsResponse(news);
    }

    private News findOrElseThrow(Long newsId) {
        return newsRepo.findById(newsId).orElseThrow(() -> new NotFoundException("News not found"));
    }

    public CustomPage<MainNewsResponse> findAll(Authentication auth, boolean likedOnly, PageRequest pageRequest) {
        CustomPage<MainNewsResponse> result = customNewsRepo.findAllByUserNicknameAndCast(auth.getName(), likedOnly, pageRequest);
        result.setCount(newsRepo.countNews(auth.getName(), likedOnly));
        return result;
    }

    public Response deleteById(Long newsId) {
        if (!newsRepo.existsById(newsId)) {
            throw new NotFoundException(
                    String.format("News with id = %s not found", newsId)
            );
        }
        newsRepo.deleteById(newsId);
        return new Response("News successfully deleted!");
    }

    public Response like(Authentication auth, Long newsId) {
        User user = userRepo.getByNickname(auth.getName());
        News news = findOrElseThrow(newsId);
        user.addLikedNews(news);
        return new Response("success");
    }

    public Response disLike(Authentication auth, Long newsId) {
        User user = userRepo.getByNickname(auth.getName());
        News news = findOrElseThrow(newsId);
        user.removeFromLikedNews(news);
        return new Response("success");
    }
}
