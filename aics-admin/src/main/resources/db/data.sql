INSERT INTO about (content, detail_category, main_category, sub_category)
VALUES ('학과 소개 내용입니다.', NULL, 'DEPT_INTRO', 'DEPT_INTRO'),
       ('커리큘럼 설명입니다.', '2019', 'EDU_ACTIVITIES', 'CURRICULUM'),
       ('연혁 더미데이터입니다.', NULL, 'DEPT_INTRO', 'HISTORY');

INSERT INTO lab (name, loc, site)
VALUES ('Lab A', '8500', 'http://lab1.kyonggi.ac.kr'),
       ('Lab B', '8520', 'https://lab2.kyonggi.ac.kr'),
       ('Lab C', '제2공학관 200', 'http://research.kyonggi.ac.kr');

-- password: password1234!
INSERT INTO "user" (id, password, name, email, phone, major, role, created_at, updated_at)
VALUES ('202412346', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG', '이한음', 'haneum@kgu.ac.kr',
        '010-2345-6789', 'AIT', 'SUPER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('202412347', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG', '박민준', 'minjun@kyonggi.ac.kr',
        '010-1234-8765', 'CSE', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('202412348', '$2a$10$NkQj6yk0Xh4QhKevjrOkouQBymXUgpKqmHQFnTUKRaVhDrRZf5OTG', '이신행', 'shinhaeng@kyonggi.ac.kr',
        '010-3456-7890', 'SSS', 'ADMIN', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

INSERT INTO professor (name, contact, email, role)
VALUES ('김교수', '010-1111-2222', 'kim@kyonggi.ac.kr', 'PROFESSOR'),
       ('이조교', '010-3333-4444', 'lee@kgu.ac.kr', 'ASSISTANT'),
       ('박교수', '010-5555-6666', 'park@kyonggi.ac.kr', 'PROFESSOR');

INSERT INTO file_entity (logical_name, physical_path)
VALUES ('학과소개파일', '/files/about/dept_intro.pdf'),
       ('교육활동파일', '/files/edu/activities.pdf'),
       ('커리큘럼파일', '/files/edu/curriculum.pdf'),
       ('연구 성과 이미지', '/files/carousel/research_results.jpg'),
       ('학과 행사 이미지', '/files/carousel/department_event.jpg'),
       ('동아리 활동 이미지', '/files/carousel/club_activity.jpg'),
       ('캠퍼스 전경 이미지', '/files/carousel/campus_view.jpg'),
       ('졸업식 이미지', '/files/carousel/graduation_ceremony.jpg');

INSERT INTO carousel (file_id, text, link)
VALUES (4, '최신 연구 성과를 확인하세요.', 'https://kyonggi.ac.kr/research'),
       (5, '학과 행사 사진입니다.', 'https://kyonggi.ac.kr/events'),
       (6, '동아리 활동을 소개합니다.', 'https://kyonggi.ac.kr/clubs'),
       (7, '아름다운 캠퍼스를 감상하세요.', 'https://kyonggi.ac.kr/campus');


INSERT INTO post (title, content, category, file_id, author_id)
VALUES ('2024학년도 학과 소개 일정 안내',
        '2024학년도 학과 소개가 아래와 같은 일정으로 진행됩니다. 참여를 원하시는 분들은 해당 일정을 참고하여 신청해 주시기 바랍니다.',
        'DEPT_INFO', 3, '202412346'),
       ('동계 방학 중 실습실 사용 안내',
        '동계 방학 기간 동안 실습실 사용 신청을 받고 있습니다. 자세한 신청 방법과 이용 규정을 확인해 주세요.',
        'LESSON_INFO', NULL, '202412346'),
       ('취업 설명회 개최 안내',
        '졸업 예정자 및 재학생을 대상으로 취업 설명회를 개최합니다. 기업 소개, 취업 전략 및 질의응답 시간이 준비되어 있으니 많은 참여 바랍니다.',
        'EMPLOY_INFO', NULL, '202412346'),
       ('학과 뉴스 - 최신 연구 성과 발표',
        '우리 학과에서는 최근 AI 기반 의료 데이터 분석 연구 성과를 발표하였습니다. 자세한 내용은 연구 성과 페이지를 참고하세요.',
        'DEPT_NEWS', 5, '202412347'),
       ('졸업생 수상 소식',
        '우리 학과 졸업생이 국내 최고 권위의 학회에서 우수 논문상을 수상하였습니다. 졸업생들의 뛰어난 활약을 소개합니다.',
        'AWARDED', 8, '202412347');