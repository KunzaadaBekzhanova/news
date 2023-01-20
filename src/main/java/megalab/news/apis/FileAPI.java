package megalab.news.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import megalab.news.dtos.Response;
import megalab.news.dtos.responses.FileUploadResponse;
import megalab.news.services.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('USER')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "File API", description = "User can upload, download and delete files")
public class FileAPI {

    private final FileService fileService;

    // upload file
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "Upload File", description = "User can upload file")
    FileUploadResponse uploadFile(@RequestBody MultipartFile file) {
        return fileService.uploadFile(file);
    }

    // delete file
    @DeleteMapping("/delete")
    @Operation(summary = "Delete File", description = "User can delete file")
    Response deleteFile(@RequestParam String path) {
        return fileService.deleteFile(path);
    }

    // download
    @GetMapping("/download")
    @Operation(summary = "Download File", description = "User can download file")
    ResponseEntity<byte[]> download(@RequestParam String path) {
        return fileService.download(path);
    }
}
