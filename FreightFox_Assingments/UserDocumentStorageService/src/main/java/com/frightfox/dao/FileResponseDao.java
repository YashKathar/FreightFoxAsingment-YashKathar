package com.frightfox.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frightfox.pojo.FileResponse;

@Repository
public interface FileResponseDao extends JpaRepository<FileResponse, Long> {

}
