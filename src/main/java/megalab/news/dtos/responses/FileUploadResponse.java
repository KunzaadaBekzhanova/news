package megalab.news.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadResponse {
    private String path;
}
