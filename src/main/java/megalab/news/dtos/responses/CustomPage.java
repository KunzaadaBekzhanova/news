package megalab.news.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class CustomPage <T> {
    private List<T> objects;
    private int count;

    public CustomPage() {
    }

    public CustomPage(List<T> objects) {
        this.objects = objects;
    }
}
