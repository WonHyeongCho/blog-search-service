# blog-search-service

## 1. 개요

***
카카오에서 제공하는 블로그 검색 API를 이용하여 검색어에 해당하는 블로그를 검색하는 서비스입니다.

## 2. API 명세

***

### 2.1. 블로그 검색 API

요청 URL: /blog  
요청 방식: GET  
요청 파라미터:    
|파라미터명|타입|설명|필수여부|   
|---|---|---|---|   
|query|string|검색어|O|    
|sort|string|정렬 옵션|X|   
|page|integer|페이지 번호|X|     
|size|integer|페이지 사이즈|X|    


