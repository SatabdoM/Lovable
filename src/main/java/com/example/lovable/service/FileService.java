package com.example.lovable.service;

import com.example.lovable.dto.project.FileContentResponse;
import com.example.lovable.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, Long userId, String path);
}
