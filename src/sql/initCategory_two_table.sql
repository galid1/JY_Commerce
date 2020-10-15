-- ================ Root 카테고리
insert into categories(category_name) values ('패션');
insert into categories(category_name) values ('가전/디지털');
insert into categories(category_name) values ('도서');
insert into categories(category_name) values ('식품');


-- =============== Level 2 카테고리
-- 패션
insert into categories(category_name) values ('여성');
insert into categories(category_name) values ('남성');
insert into categories(category_name) values ('아동');;
insert into categories(category_name) values ('스포츠');;
insert into categories(category_name) values ('잡화');;

-- 가전
insert into categories(category_name) values ('컴퓨터');
insert into categories(category_name) values ('냉장고');
insert into categories(category_name) values ('청소기');
insert into categories(category_name) values ('세탁기/건조기');

-- 도서
insert into categories(category_name) values ('여행');
insert into categories(category_name) values ('역사');
insert into categories(category_name) values ('예술');
insert into categories(category_name) values ('공학/과학');

-- 식품
insert into categories(category_name) values ('과일');
insert into categories(category_name) values ('채소');
insert into categories(category_name) values ('생수/음료');
insert into categories(category_name) values ('수산물');
insert into categories(category_name) values ('축산');

-- =============== Level 3 카테고리행
-- === 패션
-- 여성
insert into categories(category_name) values ('티');
insert into categories(category_name) values ('원피스');
insert into categories(category_name) values ('블라우스');
insert into categories(category_name) values ('바지/레깅스');

-- 남성
insert into categories(category_name) values ('티');
insert into categories(category_name) values ('맨투맨/후드');
insert into categories(category_name) values ('셔츠');
insert into categories(category_name) values ('바지/청바지');

-- 아동
insert into categories(category_name) values ('여아');
insert into categories(category_name) values ('남아');
insert into categories(category_name) values ('공용');

-- 스포츠
insert into categories(category_name) values ('여성');
insert into categories(category_name) values ('남성');
insert into categories(category_name) values ('유아');

-- 신발/가방/잡화
insert into categories(category_name) values ('시계');
insert into categories(category_name) values ('신발');
insert into categories(category_name) values ('가방');
insert into categories(category_name) values ('모자');

-- === 가전
-- 컴퓨터
insert into categories(category_name) values ('노트북');
insert into categories(category_name) values ('데스크탑');
insert into categories(category_name) values ('모니터');
insert into categories(category_name) values ('키보드/마우스');

-- 냉장고
insert into categories(category_name) values ('양문형냉장고');
insert into categories(category_name) values ('3/4도어냉장고');
insert into categories(category_name) values ('미니냉장고');
insert into categories(category_name) values ('김치냉장고');

-- 청소기
insert into categories(category_name) values ('무선/스틱청소기');
insert into categories(category_name) values ('진공청소기');
insert into categories(category_name) values ('로봇청소기');
insert into categories(category_name) values ('스팀청소기');

-- 세탁기/건조기
insert into categories(category_name) values ('세탁기');
insert into categories(category_name) values ('건조기');
insert into categories(category_name) values ('탈수기');


-- === 도서
-- 여행
insert into categories(category_name) values ('국내여행');
insert into categories(category_name) values ('해외여행');

-- 역사
insert into categories(category_name) values ('한국사');
insert into categories(category_name) values ('중국사');
insert into categories(category_name) values ('서양사');

-- 예술
insert into categories(category_name) values ('건축');
insert into categories(category_name) values ('미술');
insert into categories(category_name) values ('음악');
insert into categories(category_name) values ('무용');

-- 공학/과학
insert into categories(category_name) values ('화학');
insert into categories(category_name) values ('수학');
insert into categories(category_name) values ('물리');
insert into categories(category_name) values ('공학');

-- === 식품
-- 과일
insert into categories(category_name) values ('사과/배');
insert into categories(category_name) values ('귤/감');
insert into categories(category_name) values ('수박');
insert into categories(category_name) values ('딸기');

-- 채소
insert into categories(category_name) values ('콩나물');
insert into categories(category_name) values ('두부');
insert into categories(category_name) values ('당근');
insert into categories(category_name) values ('오이');

-- 생수/음료
insert into categories(category_name) values ('생수/탄산수');
insert into categories(category_name) values ('과일음료');
insert into categories(category_name) values ('커피');
insert into categories(category_name) values ('탄산/스포츠음료');

-- 수산물
insert into categories(category_name) values ('생선');
insert into categories(category_name) values ('오징어');
insert into categories(category_name) values ('새우');
insert into categories(category_name) values ('멸치');

-- 축산
insert into categories(category_name) values ('소고기');
insert into categories(category_name) values ('돼지고기');
insert into categories(category_name) values ('닭/오리고기');
insert into categories(category_name) values ('양고기');




# ================== category 잇기
# Level 2
# 패션
insert into sub_categories values(1, 5);
insert into sub_categories values(1, 6);
insert into sub_categories values(1, 7);
insert into sub_categories values(1, 8);
insert into sub_categories values(1, 9);
# 가전
insert into sub_categories values(2,10);
insert into sub_categories values(2,11);
insert into sub_categories values(2,12);
insert into sub_categories values(2,13);
# 도서
insert into sub_categories values(3,14);
insert into sub_categories values(3,15);
insert into sub_categories values(3,16);
insert into sub_categories values(3,17);
# 식품
insert into sub_categories values(4,18);
insert into sub_categories values(4,19);
insert into sub_categories values(4,20);
insert into sub_categories values(4,21);
insert into sub_categories values(4,22);



# ==================== Level 3
# 여성
insert into sub_categories values(5,23);
insert into sub_categories values(5,24);
insert into sub_categories values(5,25);
insert into sub_categories values(5,26);
# 남성
insert into sub_categories values(6,27);
insert into sub_categories values(6,28);
insert into sub_categories values(6,29);
insert into sub_categories values(6,30);
# 아동
insert into sub_categories values(7,31);
insert into sub_categories values(7,32);
insert into sub_categories values(7,33);
# 스포츠
insert into sub_categories values(8,34);
insert into sub_categories values(8,35);
insert into sub_categories values(8,36);
# 잡화
insert into sub_categories values(9,37);
insert into sub_categories values(9,38);
insert into sub_categories values(9,39);
insert into sub_categories values(9,40);



# ============
# 컴퓨터
insert into sub_categories values(10,41);
insert into sub_categories values(10,42);
insert into sub_categories values(10,43);
insert into sub_categories values(10,44);
# 냉장고
insert into sub_categories values(11,45);
insert into sub_categories values(11,46);
insert into sub_categories values(11,47);
insert into sub_categories values(11,48);
# 청소기
insert into sub_categories values(12,49);
insert into sub_categories values(12,50);
insert into sub_categories values(12,51);
insert into sub_categories values(12,52);
# 세탁기/건조기
insert into sub_categories values(13,53);
insert into sub_categories values(13,54);
insert into sub_categories values(13,55);



# ============
# 여행
insert into sub_categories values(14,56);
insert into sub_categories values(14,57);
# 역사
insert into sub_categories values(15,58);
insert into sub_categories values(15,59);
insert into sub_categories values(15,60);
# 예술
insert into sub_categories values(16,61);
insert into sub_categories values(16,62);
insert into sub_categories values(16,63);
insert into sub_categories values(16,64);
# 공학/과학
insert into sub_categories values(17,65);
insert into sub_categories values(17,66);
insert into sub_categories values(17,67);
insert into sub_categories values(17,68);

# ===============
# 과일
insert into sub_categories values(18,69);
insert into sub_categories values(18,70);
insert into sub_categories values(18,71);
insert into sub_categories values(18,72);
# 채소
insert into sub_categories values(19,73);
insert into sub_categories values(19,74);
insert into sub_categories values(19,75);
insert into sub_categories values(19,76);
# 생수/음료
insert into sub_categories values(20,77);
insert into sub_categories values(20,78);
insert into sub_categories values(20,79);
insert into sub_categories values(20,80);
# 수산물
insert into sub_categories values(21,81);
insert into sub_categories values(21,82);
insert into sub_categories values(21,83);
insert into sub_categories values(21,84);
# 축산
insert into sub_categories values(22,85);
insert into sub_categories values(22,86);
insert into sub_categories values(22,87);
insert into sub_categories values(22,88);