package megalab.news.services;

import lombok.RequiredArgsConstructor;
import megalab.news.dtos.requests.CategoryRequest;
import megalab.news.dtos.responses.CategoryResponse;
import megalab.news.models.Category;
import megalab.news.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;


    public List<CategoryResponse> findAll() {
        return categoryRepo.findAllAndCast();
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryRepo.save(Category.builder().name(categoryRequest.name()).build());
        return new CategoryResponse(category.getId(), category.getName());
    }
}
