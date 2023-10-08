#  목차
1. [Review 프로젝트 개요](!Review-프로젝트-개요)
2. [주요 변경사항](!🚀-주요-변경사항)
3. [느낀점](!💡-느낀점)
<br/><br/>

# Review 프로젝트 개요
기존 모놀리식 아키텍처로 구성된 Catchmind 프로젝트에서 <span style='background-color: #FF3D00'>Review 도메인</span>을 분리해 처리하는 프로젝트입니다.
![msa](https://github.com/jonghechoi/review_msa/assets/57426066/bfc11e95-8977-413c-90d5-e04e4d65fe2f)


<br/><br/>

# 🚀 주요 변경사항
  - ### JPA
    Spring Data JPA를 사용하여 기존 mapper 방식을 수정했습니다.  
    review 도메인과 관련 있는 member, shop, reservation 등도 일단은 함께 JPA를 사용하여 ORM 방식으로 데이터를 처리했니다.
  - ### Redis
    사용자가 리뷰 데이터를 생성 or 업데이트 할 때 DB에 있는 데이터가 Redis에 전달되고 get 요청이 오면 Redis에 있는 리뷰가 보여질 수 있도록 했습니다.

<br/><br/>

# 💡 개선 사항
  1. TDD를 메소드 단위로만 구성했는데 통합 테스트까지 필요해 보임
  2. 메인에 보여지는 우수리뷰의 비즈니스 로직을 구체화하여 추가해야 함
  3. API Gateway를 두고 추가적인 라우팅, 로깅, 보안 등을 고려해 볼 것
