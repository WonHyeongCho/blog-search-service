# blog-search-service

## 1. 개요

카카오에서 제공하는 블로그 검색 API를 이용하여 검색어에 해당하는 블로그를 검색하는 서비스입니다.

## 2. API 명세

### 2.1. GET /blog - 블로그 검색 API

#### 요청 파라미터:

|파라미터명|타입|설명|필수여부|  
|---|---|---|---|   
|query|string|검색 하고자 하는 키워드|O|    
|sort|string|정렬 옵션, accuracy: 정확도순 or recency: 날짜순, 기본값: accuracy|X|  
|page|integer|페이지 번호, 1 ~ 50 사이의 값, 기본 값 1|X|     
|size|integer|페이지 사이즈, 1 ~ 50 사이의 값, 기본 값 10|X|     

#### 응답 데이터:

| 응답 필드 명 | 타입          | 설명             |  
|-------------|-------------|----------------|   
| page | integer     | 검색 페이지         |                       
| size | string      | 검색된 문서 개수      |                  
| total_page | integer     | 검색된 총 페이지 개수   |              
| total_count | integer     | 검색된 총 문서 개수    |                
| sort | string      | 정렬 방식          |                         
| documents | object list | 검색된 블로그 문서 리스트 |
| documents.title | string | 문서 제목          |
| documents.contents | string | 문서 내용          |
| documents.url | string | 문서 URL         |
| documents.blogname | string | 문서 이름          |
| documents.thumbnail | string | 문서 썸네일 URL     |
| documents.datetime | string | 문서 작성일        |

#### 응답 예시:

  ```json
  {
    "page": 1,
    "size": 2,
    "total_page": 800,
    "total_count": 322340,
    "sort": "accuracy",
    "documents": [
        {
            "title": "<b>판교</b>도서관 <b>맛집</b> 피제리아 비니스 화덕피자전문점 후기",
            "contents": "<b>판교</b><b>맛집</b> 피제리아 비니스는 화덕피자전문점입니다. 저희가 육아휴직이라서 평일 점심에 갔는데요. 이렇게 여유 있는 사람들이 많구나 싶을 정도로 <b>판교</b>도서관 <b>맛집</b> 피제리아 비니스 홀이 꽉 찼네요. 한 테이블이 있어서 입장할 수 있었습니다. 늘 도미노피자, 피자헛 등 집에서 배달해 먹는 피자만 먹다가 이탈리아...",
            "url": "https://memoryseung1224.tistory.com/1478",
            "blogname": "사진은 추억을 닮다",
            "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/7Ls9PF44n9s",
            "datetime": "2023-11-08T21:50:16Z"
        },
        {
            "title": "<b>판교</b><b>맛집</b>|매일식당+내돈내산 (23.10.29)",
            "contents": "거 같아요!! 매일 쓰시는 블로그 분들은 정말 대단하다고 느낍니다... 저도 최대한 글 많이 쓰고 많은 정보를 전달드리겠습니다. 오늘은 <b>판교</b>역 근처에 위치하고 있는 <b>판교</b><b>맛집</b> 매일식당 에 방문 및 알아보려고 합니다. 사전 평은 여러번 가는 음식점이지만, 김치가 변경되어서 아쉽다... <b>판교</b>의 <b>맛집</b>인 <b>판교</b>매일식당...",
            "url": "https://wshjlove.tistory.com/32",
            "blogname": "잡쓰",
            "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/9vphSziaxph",
            "datetime": "2023-11-14T11:38:35Z"
        }
    ],
    "sort": "accuracy"
}
  ```






