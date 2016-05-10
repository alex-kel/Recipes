package ru.dz.recipes.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ru.dz.recipes.service.DropboxService

/**
 * Created by Alex on 10.05.16.
 */
@RestController
class FileUploadController {

    @Autowired DropboxService dropboxService

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        def url = dropboxService.uploadFile(file)
        new ResponseEntity(url, HttpStatus.OK)
    }
}
