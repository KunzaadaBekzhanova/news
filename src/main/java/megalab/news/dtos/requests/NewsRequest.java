package megalab.news.dtos.requests;

import java.util.List;

public record NewsRequest(String newsCover,
                          String heading,
                          String shortDescription,
                          String newsContent,
                          List<Long> categories) {
}
