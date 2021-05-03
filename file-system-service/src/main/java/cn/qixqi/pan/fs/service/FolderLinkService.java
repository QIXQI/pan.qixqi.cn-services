package cn.qixqi.pan.fs.service;

import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.repository.FolderLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderLinkService {

    @Autowired
    private FolderLinkRepository folderLinkRepository;

    public FolderLink getFolderLink(String folderId){
        return folderLinkRepository.findByFolderId(folderId);
    }

    public List<FolderLink> getFolderLinks(String uid){
        return folderLinkRepository.findByUid(uid);
    }

    public List<FolderLink> getAllFolderLink(){
        return folderLinkRepository.findAll();
    }

    public FolderLink addFolderLink(FolderLink folderLink){
        return folderLinkRepository.save(folderLink);
    }

    public FolderLink updateFolderLink(FolderLink folderLink){
        return folderLinkRepository.save(folderLink);
    }

    public void deleteFolderLink(String folderId){
        folderLinkRepository.deleteById(folderId);
    }
}
