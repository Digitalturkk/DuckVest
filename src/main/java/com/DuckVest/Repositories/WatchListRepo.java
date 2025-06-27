package com.DuckVest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.DuckVest.Models.WatchList;

public interface WatchListRepo extends JpaRepository<WatchList, Long> {
}
