package megalab.news.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import megalab.news.models.News;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    private Long id;
    private String newsCover;
    private LocalDate date;
    private String heading;
    private String shortDescription;

    public NewsResponse(News news) {
        this.id = news.getId();
        this.newsCover = news.getNewsCover();
        this.date = news.getDate();
        this.heading = news.getHeading();
        this.shortDescription = news.getShortDescription();
    }
}
