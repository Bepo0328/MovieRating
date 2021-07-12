package kr.co.bepo.movierating.utillity

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kr.co.bepo.movierating.DBKey
import kr.co.bepo.movierating.domain.model.Movie

class MovieDataGenerator {

    fun generate() {
        val firestore = Firebase.firestore

        movieData.forEach {
            firestore.collection(DBKey.COLLECTION_MOVIES).add(it)
        }
    }

    private val movieData = listOf(
        Movie(
            isFeatured = true,
            title = "블랙 위도우",
            actors = "스칼렛 요한슨 ,플로렌스 퓨 ,레이첼 와이즈 ,데이빗 하버",
            country = "미국",
            director = "케이트 쇼트랜드",
            genre = "#액션 #어드벤처 #SF",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/33/DPF022685_01.jpg",
            rating = "12세이상관람가",
            averageScore = 4.3f,
            numberOfScore = 1_366_065,
            releaseYear = 2021,
            runtime = 134
        ),
        Movie(
            isFeatured = false,
            title = "발신제한",
            actors = "조우진 , 이재인 , 진경",
            country = "한국",
            director = "김창주",
            genre = "#스릴러 #드라마",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/30/DPK017121_01.jpg",
            rating = "15세이상관람가",
            averageScore = 3.4f,
            numberOfScore = 894_237,
            releaseYear = 2021,
            runtime = 94
        ),
        Movie(
            isFeatured = false,
            title = "크루엘라",
            actors = "엠마 스톤 , 엠마 톰슨 , 조엘 프라이",
            country = "미국",
            director = "크레이그 질레스피",
            genre = "#드라마 #범죄 #코미디",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/28/DPF022415_01.jpg",
            rating = "12세이상관람가",
            averageScore = 4.4f,
            numberOfScore = 1_856_052,
            releaseYear = 2021,
            runtime = 134
        ),
        Movie(
            isFeatured = false,
            title = "콰이어트 플레이스 2",
            actors = "에밀리 블런트 , 킬리언 머피 , 노아 주프 , 밀리센트 시몬스",
            country = "미국",
            director = "존 크래신스키",
            genre = "#스릴러",
            posterUrl = "http://file.koreafilm.or.kr/poster/00/05/47/DPF020083_01.jpg",
            rating = "15세이상관람가",
            averageScore = 3.7f,
            numberOfScore = 842_643,
            releaseYear = 2021,
            runtime = 97
        ),
        Movie(
            isFeatured = false,
            title = "랑종",
            actors = "나릴야 군몽콘켓 , 싸와니 우툼마 , 씨라니 얀키띠칸",
            country = "한국, 태국",
            director = "반종 피산다나쿤",
            genre = "#공포 #스릴러 #드라마",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/32/DPK017224_01.jpg",
            rating = "청소년관람불가",
            averageScore = 3.0f,
            numberOfScore = 47_687,
            releaseYear = 2021,
            runtime = 131
        ),
        Movie(
            isFeatured = false,
            title = "루카",
            actors = "제이콥 트렘블레이 ,잭 딜런 그레이저 ,엠마 버만",
            country = "미국",
            director = "엔리코 카사로사",
            genre = "#애니메이션 #어드벤처 #가족 #판타지",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/28/DPF022443_01.jpg",
            rating = "전체관람가",
            averageScore = 4.1f,
            numberOfScore = 357_196,
            releaseYear = 2021,
            runtime = 95
        ),
        Movie(
            isFeatured = false,
            title = "킬러의 보디가드 2",
            actors = "라이언 레이놀즈 ,사무엘 L. 잭슨 ,셀마 헤이엑",
            country = "미국, 영국",
            director = "패트릭 휴즈",
            genre = "#액션 #코미디 #범죄",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/31/DPF022552_01.jpg",
            rating = "청소년관람불가",
            averageScore = 3.1f,
            numberOfScore = 378_058,
            releaseYear = 2021,
            runtime = 117
        ),
        Movie(
            isFeatured = false,
            title = "미드나이트",
            actors = "진기주 ,위하준 ,박훈",
            country = "한국",
            director = "권오승",
            genre = "#스릴러",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/31/DPK017183_01.jpg",
            rating = "15세이상관람가",
            averageScore = 2.4f,
            numberOfScore = 105_101,
            releaseYear = 2021,
            runtime = 103
        ),
        Movie(
            isFeatured = false,
            title = "이스케이프 룸 2: 노 웨이 아웃",
            actors = "테일러 러셀 ,로건 밀러 ,인디야 무어",
            country = "미국",
            director = "애덤 로비텔",
            genre = "#액션 #공포 #스릴러",
            posterUrl = "http://file.koreafilm.or.kr/poster/99/17/32/DPF022626_01.jpg",
            rating = "15세이상관람가",
            averageScore = 3.0f,
            numberOfScore = 290,
            releaseYear = 2021,
            runtime = 88
        )
    )
}