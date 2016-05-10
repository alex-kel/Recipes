package ru.dz.recipes.service

import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.FileMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * Created by Alex on 10.05.16.
 */
@Service
class DropboxService {

    @Autowired DbxClientV2 dropboxClient

    String uploadFile(MultipartFile file) {
        def path = "/${file.getSize()}${Calendar.getInstance().get(Calendar.SECOND)}${file.getOriginalFilename()}"
        FileMetadata metadata = dropboxClient.files().uploadBuilder(path).uploadAndFinish(file.getInputStream())
        dropboxClient.sharing().createSharedLinkWithSettings(path).getUrl().replace("dl=0", "dl=1")
    }
}
