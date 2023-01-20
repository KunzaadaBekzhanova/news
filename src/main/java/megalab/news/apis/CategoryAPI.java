package megalab.news.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.news.dtos.requests.CategoryRequest;
import megalab.news.dtos.responses.CategoryResponse;
import megalab.news.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Category API", description = "Any users can get all categories")
@PermitAll
public class CategoryAPI {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Find all categories", description = "User can find all categories")
    List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save new category", description = "User can save new category")
    CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }
}