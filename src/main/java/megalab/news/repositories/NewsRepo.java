package megalab.news.repositories;


import megalab.news.dtos.responses.NewsResponse;
import megalab.news.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, Long> {

    @Query("""
            select new megalab.news.dtos.responses.NewsResponse(
            n.id, n.newsCover, n.date, n.heading, n.shortDescription
            )
            from News n
            join n.writer w where w.nickname = ?1
            order by n.date desc 
            """)
    List<NewsResponse> findAllByUserNickname(String name);

    @Query(nativeQuery = true, value = """
            select count(*) from news n
            left join users u on n.id in (select uln.liked_news_id from users_liked_news uln where uln.user_id = u.id) and u.nickname = ?1
            where case when ?2 then u.id is not null else true end
            """)
    int countNews(String nickname, boolean trueOnly);
}

