package megalab.news.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megalab.news.models.News;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullNewsResponse {
    private Long id;
    private LocalDate date;
    private String heading;
    private String shortDescription;
    private String newsCover;
    private String newsContent;
    private List<CommentResponse> comments;

    public FullNewsResponse(News news) {
        this.id = news.getId();
        this.date = news.getDate();
        this.heading = news.getHeading();
        this.shortDescription = news.getShortDescription();
        this.newsCover = news.getNewsCover();
        this.newsContent = news.getNewsContent();
    }
}
