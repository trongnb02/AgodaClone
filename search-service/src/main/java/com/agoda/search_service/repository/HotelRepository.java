package com.agoda.search_service.repository;

import com.agoda.search_service.model.Hotel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HotelRepository extends ElasticsearchRepository<Hotel, String> {
}
