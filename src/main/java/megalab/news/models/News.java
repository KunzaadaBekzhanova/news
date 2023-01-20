package megalab.news.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megalab.news.dtos.responses.MainNewsResponse;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "news")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User writer;

    // this field will be path of an image
    private String newsCover;

    private String heading;

    private String shortDescription;

    // Текст новости
    @Column(length = 10000)
    private String newsContent;

    private LocalDate date;

    @ManyToMany(mappedBy = "newsList", cascade = CascadeType.MERGE)
    private List<Category> categories;

    @OneToMany(mappedBy = "news")
    private List<Comment> commentList;
}
