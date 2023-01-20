package megalab.news.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainNewsResponse extends NewsResponse {
    private boolean liked;

    public MainNewsResponse(Long id, String newsCover, LocalDate date, String heading, String shortDescription, boolean liked) {
        super(id, newsCover, date, heading, shortDescription);
        this.liked = liked;
    }
}