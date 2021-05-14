package cn.qixqi.pan.filesharing.repository;

import cn.qixqi.pan.filesharing.model.FileShare;
import cn.qixqi.pan.filesharing.model.FileShareLink;

public interface UpdateRepository {
    long updateFileShare(FileShare fileShare);
    long updateFileShareLink(FileShareLink fileShareLink);
}
