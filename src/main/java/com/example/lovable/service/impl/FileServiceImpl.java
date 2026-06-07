package com.example.lovable.service.impl;

import com.example.lovable.dto.project.FileContentResponse;
import com.example.lovable.dto.project.FileNode;
import com.example.lovable.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, Long userId, String path) {
        return null;
    }
}
