package megalab.news.repositories;


import lombok.RequiredArgsConstructor;
import megalab.news.dtos.responses.CustomPage;
import megalab.news.dtos.responses.MainNewsResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomNewsRepo {

    private final JdbcTemplate jdbcTemplate;


    public CustomPage<MainNewsResponse> findAllByUserNicknameAndCast(String nickname, boolean trueOnly, PageRequest pageRequest) {
        List<MainNewsResponse> mainNewsResponseList = jdbcTemplate.query("""
                        select
                            n.id as id,
                            n.news_cover as news_cover,
                            n.date as date,
                            n.heading as heading,
                            n.short_description as short_description,
                            u.id is not null as liked
                                from news n
                        left join users u on n.id in (select uln.liked_news_id from users_liked_news uln where uln.user_id = u.id) and u.nickname = ?
                        where case when ? then u.id is not null else true end
                        limit ? offset ?
                        """,
                ps -> {
                    ps.setString(1, nickname);
                    ps.setBoolean(2, trueOnly);
                    ps.setLong(3, ((long) pageRequest.getPageNumber() * pageRequest.getPageSize()));
                    ps.setLong(4, ((long) (pageRequest.getPageNumber() - 1) * pageRequest.getPageSize()));
                }, (rs, rn) -> new MainNewsResponse(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6)
                ));

        return new CustomPage<>(mainNewsResponseList);
    }
}

