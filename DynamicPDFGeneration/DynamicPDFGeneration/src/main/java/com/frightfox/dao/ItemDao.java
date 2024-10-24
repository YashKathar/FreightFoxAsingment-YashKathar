package com.frightfox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frightfox.pojo.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
