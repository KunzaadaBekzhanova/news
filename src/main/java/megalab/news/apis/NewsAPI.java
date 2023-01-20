package megalab.news.apis;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.news.dtos.Response;
import megalab.news.dtos.requests.NewsRequest;
import megalab.news.dtos.responses.CustomPage;
import megalab.news.dtos.responses.FullNewsResponse;
import megalab.news.dtos.responses.MainNewsResponse;
import megalab.news.dtos.responses.NewsResponse;
import megalab.news.services.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "News API", description = "User can create, update or delete news")
public class NewsAPI {

    private final NewsService newsService;

    // save
    @PostMapping
    @Operation(summary = "Add news", description = "User can add news")
    NewsResponse save(Authentication auth, @RequestBody NewsRequest newsRequest) {
        return newsService.save(auth, newsRequest);
    }

    // find all
    @GetMapping
    @Operation(summary = "All news", description = "User can see all news")
    CustomPage<MainNewsResponse> findAll(Authentication auth,
                                         @RequestParam(required = false, defaultValue = "false") boolean likedOnly,
                                         @RequestParam(required = false, defaultValue = "1") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size) {
        return newsService.findAll(auth, likedOnly, PageRequest.of(page, size));
    }

    // find by id
    @GetMapping("/{newsId}")
    @PermitAll
    @Operation(summary = "Find news by ID", description = "User find news by ID")
    FullNewsResponse findById(@PathVariable Long newsId) {
        return newsService.findById(newsId);
    }

    // delete
    @DeleteMapping("/{newsId}")
    @Operation(summary = "Delete news", description = "User can delete news")
    Response deleteById(@PathVariable Long newsId) {
        return newsService.deleteById(newsId);
    }

    // like
    @PutMapping("/{newsId}/like")
    @Operation(summary = "Like", description = "User can put LIKE on news")
    Response like(Authentication auth, @PathVariable Long newsId) {
        return newsService.like(auth, newsId);
    }

    // dislike
    @PutMapping("/{newsId}/dislike")
    @Operation(summary = "Dislike", description = "User can put DISLIKE on news")
    Response disLike(Authentication auth, @PathVariable Long newsId) {
        return newsService.disLike(auth, newsId);
    }
}
