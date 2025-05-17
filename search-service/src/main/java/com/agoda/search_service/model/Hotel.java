package com.agoda.search_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "hotels")
@Setting(settingPath = "esconfig/elastic-analyzer.json")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String address;

    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String city;

    @Field(type = FieldType.Keyword)
    private String phone;

    @Field(type = FieldType.Keyword)
    private String vendorId;

    @Field(type = FieldType.Keyword)
    private String propertyType;

    @Field(type = FieldType.Keyword)
    private List<String> facilities = new ArrayList<>();

    @Field(type = FieldType.Nested)
    private List<Room> rooms = new ArrayList<>();

    @Field(type = FieldType.Text)
    private String earliestCheckInTime;

    @Field(type = FieldType.Text)
    private String latestCheckInTime;

    @Field(type = FieldType.Text)
    private String standardCheckOutTime;

    @Field(type = FieldType.Text)
    private String latestCheckOutTime;

    @Field(type = FieldType.Double)
    private Double lateCheckoutFee;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Room {
        private String id;

        @Field(type = FieldType.Text)
        private String description;

        @Field(type = FieldType.Double)
        private Double price;

        @Field(type = FieldType.Integer)
        private Integer capacity;

        @Field(type = FieldType.Boolean)
        private Boolean availability = true;

        @Field(type = FieldType.Keyword)
        private List<String> amenities = new ArrayList<>();

        @Field(type = FieldType.Keyword)
        private String bedType;
    }
}
