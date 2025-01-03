-- about
INSERT INTO about (content, detail_category, main_category, sub_category, created_at, updated_at)
VALUES ('학과 소개 내용입니다.', NULL, 'DEPT_INTRO', 'DEPT_INTRO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('커리큘럼 설명입니다.', '2019', 'EDU_ACTIVITIES', 'CURRICULUM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('연혁 더미데이터입니다.', NULL, 'DEPT_INTRO', 'HISTORY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('연구 활동 소개입니다.', NULL, 'EDU_ACTIVITIES', 'LEARNING_ACTIVITIES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('학생 지원 서비스 안내입니다.', NULL, 'DEPT_INTRO', 'DEPT_INTRO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('졸업 요건 설명입니다.', NULL, 'DEPT_INTRO', 'CURRICULUM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('학과 소식지 발행 안내입니다.', NULL, 'DEPT_INTRO', 'DEPT_INTRO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- lab
INSERT INTO lab (name, loc, site, created_at, updated_at)
VALUES ('Lab A', '8500', 'http://lab1.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab B', '8520', 'https://lab2.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab C', '제2공학관 200', 'http://research.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab D', '8600', 'http://lab4.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab E', '8700', 'https://lab5.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab F', '8800', 'http://lab6.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab G', '8900', 'https://lab7.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab H', '9000', 'http://lab8.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab I', '9100', 'https://lab9.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab J', '9200', 'http://lab10.kyonggi.ac.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- club
INSERT INTO club (name, description, site, created_at, updated_at)
VALUES ('SSF', '웹 개발 동아리 SSF입니다.', 'https://www.ssf.or.kr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('K.Knock', '경기대학교 정보보안 동아리 K.Knock입니다.', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('C-Lab', '경기대학교 AI컴퓨터공학부 개발동아리입니다.', 'https://clab.page', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('InQ', '개발자 플랫폼 동아리 InQ입니다.', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- password: password1234!
INSERT INTO "user" (id, password, name, email, phone, major, role, created_at, updated_at)
VALUES ('202412346', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG',
        '이한음', 'haneum@kgu.ac.kr', '010-2345-6789', 'AIT', 'SUPER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('202412347', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG',
        '박민준', 'minjun@kyonggi.ac.kr', '010-1234-8765', 'CSE', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('202412348', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG',
        '이신행', 'shinhaeng@kyonggi.ac.kr', '010-3456-7890', 'SSS', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('202412349', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG',
        '홍길동', 'hong@kyonggi.ac.kr', '010-5678-1234', 'CSE', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('202412350', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG',
        '김철수', 'kim@kyonggi.ac.kr', '010-8765-4321', 'CSE', 'SUPER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- professor
INSERT INTO professor (name, contact, email, role, created_at, updated_at)
VALUES ('김교수', '010-1111-2222', 'kim@kyonggi.ac.kr', 'PROFESSOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('이조교', '010-3333-4444', 'lee@kgu.ac.kr', 'ASSISTANT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('박교수', '010-5555-6666', 'park@kyonggi.ac.kr', 'PROFESSOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('최교수', '010-7777-8888', 'choi@kyonggi.ac.kr', 'PROFESSOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('강교수', '010-9999-0000', 'kang@kyonggi.ac.kr', 'PROFESSOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- fileEntity
INSERT INTO file_entity (logical_name, physical_path, extension, file_size, created_at, updated_at)
VALUES ('학과소개파일', '/files/about/dept_intro.pdf', 'pdf', '12345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('교육활동파일', '/files/edu/activities.pdf', 'pdf', '23456', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('커리큘럼파일', '/files/edu/curriculum.pdf', 'pdf', '34567', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('연구 성과 이미지', '/files/carousel/research_results.jpg', 'jpg', '45678', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('학과 행사 이미지', '/files/carousel/department_event.jpg', 'jpg', '56789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('동아리 활동 이미지', '/files/carousel/club_activity.jpg', 'jpg', '67890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('캠퍼스 전경 이미지', '/files/carousel/campus_view.jpg', 'jpg', '78901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('졸업식 이미지', '/files/carousel/graduation_ceremony.jpg', 'jpg', '89012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('취업 설명회 자료', '/files/employment/info.pdf', 'pdf', '54321', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('연구 프로젝트 소개', '/files/research/project_intro.pdf', 'pdf', '65432', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- carousel
INSERT INTO carousel (file_id, text, link, created_at, updated_at)
VALUES (4, '최신 연구 성과를 확인하세요.', 'https://kyonggi.ac.kr/research', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, '학과 행사 사진입니다.', 'https://kyonggi.ac.kr/events', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, '동아리 활동을 소개합니다.', 'https://kyonggi.ac.kr/clubs', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (7, '아름다운 캠퍼스를 감상하세요.', 'https://kyonggi.ac.kr/campus', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (8, '졸업식 하이라이트를 확인하세요.', 'https://kyonggi.ac.kr/graduation', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (9, '취업 설명회 자료를 다운로드하세요.', 'https://kyonggi.ac.kr/employment', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (10, '새로운 연구 프로젝트를 소개합니다.', 'https://kyonggi.ac.kr/research/project', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- post
INSERT INTO post (title, content, category, file_id, author_id, created_at, updated_at, views, is_pinned)
VALUES ('2024학년도 학과 소개 일정 안내',
        '2024학년도 학과 소개가 아래와 같은 일정으로 진행됩니다. 참여를 원하시는 분들은 해당 일정을 참고하여 신청해 주시기 바랍니다.',
        'DEPT_INFO', 1, '202412346', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('동계 방학 중 실습실 사용 안내',
        '동계 방학 기간 동안 실습실 사용 신청을 받고 있습니다. 자세한 신청 방법과 이용 규정을 확인해 주세요.',
        'LESSON_INFO', NULL, '202412346', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('취업 설명회 개최 안내',
        '졸업 예정자 및 재학생을 대상으로 취업 설명회를 개최합니다. 기업 소개, 취업 전략 및 질의응답 시간이 준비되어 있으니 많은 참여 바랍니다.',
        'EMPLOY_INFO', 9, '202412346', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('학과 뉴스 - 최신 연구 성과 발표',
        '우리 학과에서는 최근 AI 기반 의료 데이터 분석 연구 성과를 발표하였습니다. 자세한 내용은 연구 성과 페이지를 참고하세요.',
        'DEPT_NEWS', 4, '202412347', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('졸업생 수상 소식',
        '우리 학과 졸업생이 국내 최고 권위의 학회에서 우수 논문상을 수상하였습니다. 졸업생들의 뛰어난 활약을 소개합니다.',
        'AWARDED', 8, '202412347', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('동아리 활동 현황',
        '동아리 활동 사진과 관련 자료를 확인하세요. 동아리 가입 및 활동 절차에 대해 설명합니다.',
        'DEPT_NEWS', 6, '202412348', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('캠퍼스 전경과 주요 시설 안내',
        '캠퍼스 전경 및 주요 시설에 대한 소개입니다. 사진과 함께 확인해보세요.',
        'DEPT_NEWS', 7, '202412348', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false),
       ('연구 프로젝트 발표',
        'AI 기반 연구 프로젝트에 대한 발표 자료입니다. 관심 있는 분들은 파일을 다운로드하세요.',
        'GOOD_WORKS', 10, '202412346', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, false);

-- comment
INSERT INTO comment (content, created_at, updated_at, post_id, author_id)
VALUES ('참여 신청 방법을 자세히 알고 싶습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '202412347'),
       ('일정 확인 감사합니다!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '202412348'),
       ('실습실 신청은 어디서 할 수 있나요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, '202412347'),
       ('규정 관련 자료를 추가로 공유 부탁드립니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, '202412346'),
       ('설명회 시간은 몇 시부터인가요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, '202412348'),
       ('참여 신청 링크는 어디인가요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, '202412347'),
       ('AI 연구 성과에 대한 자세한 자료를 보고 싶습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, '202412346'),
       ('다음 연구 발표 일정이 궁금합니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, '202412348'),
       ('졸업생들 대단합니다! 축하드립니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, '202412347'),
       ('우수 논문상 수상 자료를 확인할 수 있을까요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, '202412346'),
       ('동아리 가입 방법을 알고 싶습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, '202412348'),
       ('활동 사진을 보니 흥미롭네요.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, '202412347'),
       ('캠퍼스 전경이 정말 아름답네요.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, '202412346'),
       ('주요 시설 이용 안내도 포함되면 좋겠습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, '202412348'),
       ('프로젝트 자료를 다운로드했습니다. 유익하네요.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, '202412347'),
       ('연구 참여 방법이 있나요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, '202412346');
