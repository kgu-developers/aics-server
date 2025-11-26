-- about
INSERT INTO about (content, category, created_at, updated_at)
VALUES ('학과 소개 내용입니다.', 'DEPT_INTRO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('경기대학교 수원캠퍼스 8강의동에 있습니다.', 'DIRECTIONS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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
INSERT INTO professor (name, contact, email, img, office_loc ,role, created_at, updated_at)
VALUES ('김교수', '010-1111-2222', 'kim@kyonggi.ac.kr', 'https://image.com/professor/profile/image1',
        '8501호','PROFESSOR', '2025-03-03 00:00:00', '2025-03-03 00:00:00'),
       ('이조교', '010-3333-4444', 'lee@kgu.ac.kr', 'https://image.com/professor/profile/image2',
        '제2공학관 601호' ,'ASSISTANT', '2025-03-03 01:00:00', '2025-03-03 01:00:00'),
       ('박교수', '010-5555-6666', 'park@kyonggi.ac.kr', 'https://image.com/professor/profile/image3',
        '제2공학관 601호','PROFESSOR', '2025-03-03 02:00:00', '2025-03-03 02:00:00'),
       ('최교수', '010-7777-8888', 'choi@kyonggi.ac.kr', 'https://image.com/professor/profile/none',
        '8201호','PROFESSOR', '2025-03-03 03:00:00', '2025-03-03 03:00:00'),
       ('강교수', '010-9999-0000', 'kang@kyonggi.ac.kr', 'https://image.com/professor/profile/none',
        '8201호','PROFESSOR', '2025-03-03 04:00:00', '2025-03-03 04:00:00');

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
       ('연구 프로젝트 소개', '/files/research/project_intro.pdf', 'pdf', '65432', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB A 로고 이미지', '/files/lab/LAB-A.png', 'png', '12345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB B 로고 이미지', '/files/lab/LAB-B.jpg', 'jpg', '12346', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB C 로고 이미지', '/files/lab/LAB-C.jpg', 'jpg', '12347', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB D 로고 이미지', '/files/lab/LAB-D.png', 'png', '12348', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB E 로고 이미지', '/files/lab/LAB-E.png', 'png', '12349', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB F 로고 이미지', '/files/lab/LAB-F.png', 'png', '12350', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB G 로고 이미지', '/files/lab/LAB-G.jpg', 'jpg', '12351', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB H 로고 이미지', '/files/lab/LAB-H.jpg', 'jpg', '12352', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB I 로고 이미지', '/files/lab/LAB-I.png', 'png', '12353', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('LAB J 로고 이미지', '/files/lab/LAB-J.png', 'png', '12354', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('CLUB SSF 로고 이미지', '/files/club/SSF.jpg', 'jpg', '12351', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('CLUB K.Knock 로고 이미지', '/files/club/K-Knock.jpg', 'jpg', '12352', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('CLUB C-Lab 로고 이미지', '/files/club/C-Lab.png', 'png', '12353', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('CLUB InQ 로고 이미지', '/files/club/InQ.png', 'png', '12354', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- lab
INSERT INTO lab (name, loc, site, advisor, file_id, created_at, updated_at)
VALUES ('Lab A', '8500', 'http://lab1.kyonggi.ac.kr', '김교수', 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab B', '8520', 'https://lab2.kyonggi.ac.kr', '이교수', 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab C', '제2공학관 200', 'http://research.kyonggi.ac.kr', '박교수', 13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab D', '8600', 'http://lab4.kyonggi.ac.kr', '최교수', 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab E', '8700', 'https://lab5.kyonggi.ac.kr', '조교수', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab F', '8800', 'http://lab6.kyonggi.ac.kr', '정교수', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab G', '8900', 'https://lab7.kyonggi.ac.kr', '윤교수', 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab H', '9000', 'http://lab8.kyonggi.ac.kr', '전교수', 18, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab I', '9100', 'https://lab9.kyonggi.ac.kr', '주교수', 19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Lab J', '9200', 'http://lab10.kyonggi.ac.kr', '성교수', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- club
INSERT INTO club (name, description, site, file_id, created_at, updated_at)
VALUES ('SSF', '웹 개발 동아리 SSF입니다.', 'https://www.ssf.or.kr', 21, '2025-03-03 00:00:00', '2025-03-03 00:00:00'),
       ('K.Knock', '경기대학교 정보보안 동아리 K.Knock입니다.', NULL, 22, '2025-03-03 01:00:00', '2025-03-03 01:00:00'),
       ('C-Lab', '경기대학교 AI컴퓨터공학부 개발동아리입니다.', 'https://clab.page', 23, '2025-03-03 02:00:00', '2025-03-03 02:00:00'),
       ('InQ', '개발자 플랫폼 동아리 InQ입니다.', NULL, 24, '2025-03-03 03:00:00', '2025-03-03 03:00:00');

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
        'NOTIFICATION', 1, '202412346', '2025-03-03 00:00:00', '2025-03-03 00:00:00', 0, false),
       ('동계 방학 중 실습실 사용 안내',
        '동계 방학 기간 동안 실습실 사용 신청을 받고 있습니다. 자세한 신청 방법과 이용 규정을 확인해 주세요.',
        'NOTIFICATION', NULL, '202412346', '2025-03-03 01:00:00', '2025-03-03 01:00:00', 0, false),
       ('취업 설명회 개최 안내',
        '졸업 예정자 및 재학생을 대상으로 취업 설명회를 개최합니다. 기업 소개, 취업 전략 및 질의응답 시간이 준비되어 있으니 많은 참여 바랍니다.',
        'NOTIFICATION', 9, '202412346', '2025-03-03 02:00:00', '2025-03-03 02:00:00', 0, false),
       ('학과 뉴스 - 최신 연구 성과 발표',
        '우리 학과에서는 최근 AI 기반 의료 데이터 분석 연구 성과를 발표하였습니다. 자세한 내용은 연구 성과 페이지를 참고하세요.',
        'NEWS', 4, '202412347', '2025-03-03 03:00:00', '2025-03-03 03:00:00', 0, false),
       ('졸업생 수상 소식',
        '우리 학과 졸업생이 국내 최고 권위의 학회에서 우수 논문상을 수상하였습니다. 졸업생들의 뛰어난 활약을 소개합니다.',
        'NEWS', 8, '202412347', '2025-03-03 04:00:00', '2025-03-03 04:00:00', 0, false),
       ('동아리 활동 현황',
        '동아리 활동 사진과 관련 자료를 확인하세요. 동아리 가입 및 활동 절차에 대해 설명합니다.',
        'NOTIFICATION', 6, '202412348', '2025-03-03 05:00:00', '2025-03-03 05:00:00', 0, false),
       ('캠퍼스 전경과 주요 시설 안내',
        '캠퍼스 전경 및 주요 시설에 대한 소개입니다. 사진과 함께 확인해보세요.',
        'NOTIFICATION', 7, '202412348', '2025-03-03 06:00:00', '2025-03-03 06:00:00', 0, false),
       ('연구 프로젝트 발표',
        'AI 기반 연구 프로젝트에 대한 발표 자료입니다. 관심 있는 분들은 파일을 다운로드하세요.',
        'NEWS', 10, '202412346', '2025-03-03 07:00:00', '2025-03-03 07:00:00', 0, false);

-- comment
INSERT INTO comment (content, created_at, updated_at, post_id, author_id)
VALUES ('참여 신청 방법을 자세히 알고 싶습니다.', '2025-03-03 00:01:00', '2025-03-03 00:01:00', 1, '202412347'),
       ('일정 확인 감사합니다!', '2025-03-03 00:02:00', '2025-03-03 00:02:00', 1, '202412348'),
       ('실습실 신청은 어디서 할 수 있나요?', '2025-03-03 01:01:00', '2025-03-03 01:01:00', 2, '202412347'),
       ('규정 관련 자료를 추가로 공유 부탁드립니다.', '2025-03-03 01:02:00', '2025-03-03 01:02:00', 2, '202412346'),
       ('설명회 시간은 몇 시부터인가요?', '2025-03-03 02:01:00', '2025-03-03 02:01:00', 3, '202412348'),
       ('참여 신청 링크는 어디인가요?', '2025-03-03 02:02:00', '2025-03-03 02:02:00', 3, '202412347'),
       ('AI 연구 성과에 대한 자세한 자료를 보고 싶습니다.', '2025-03-03 03:01:00', '2025-03-03 03:01:00', 4, '202412346'),
       ('다음 연구 발표 일정이 궁금합니다.', '2025-03-03 03:02:00', '2025-03-03 03:02:00', 4, '202412348'),
       ('졸업생들 대단합니다! 축하드립니다.', '2025-03-03 04:01:00', '2025-03-03 04:01:00', 5, '202412347'),
       ('우수 논문상 수상 자료를 확인할 수 있을까요?', '2025-03-03 04:02:00', '2025-03-03 04:02:00', 5, '202412346'),
       ('동아리 가입 방법을 알고 싶습니다.', '2025-03-03 05:01:00', '2025-03-03 05:01:00', 6, '202412348'),
       ('활동 사진을 보니 흥미롭네요.', '2025-03-03 05:02:00', '2025-03-03 05:02:00', 6, '202412347'),
       ('캠퍼스 전경이 정말 아름답네요.', '2025-03-03 06:01:00', '2025-03-03 06:01:00', 7, '202412346'),
       ('주요 시설 이용 안내도 포함되면 좋겠습니다.', '2025-03-03 06:02:00', '2025-03-03 06:02:00', 7, '202412348'),
       ('프로젝트 자료를 다운로드했습니다. 유익하네요.', '2025-03-03 07:01:00', '2025-03-03 07:01:00', 8, '202412347'),
       ('연구 참여 방법이 있나요?', '2025-03-03 07:02:00', '2025-03-03 07:02:00', 8, '202412346');

INSERT INTO graduation_user (name, email, advisor_professor, graduation_type, graduation_date,
                             mid_thesis_id, final_thesis_id, certificate_id, user_id, created_at, updated_at)
VALUES
    ('김철수', 'chulsoo@kyonggi.ac.kr', '이순신', '논문', '2028-02-28', 1, 2, NULL, '202512345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('이영희', 'younghee@kyonggi.ac.kr', '홍길동', '자격증', '2028-08-31', NULL, NULL, 1, '202512346', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('박민수', 'minsu@kyonggi.ac.kr', '정약용', '논문', '2029-02-28', 3, NULL, NULL, '202512347', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('정수진', 'sujin@kyonggi.ac.kr', '김이박', '논문', '2030-02-28', 4, 5, NULL, '202512349', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('최동욱', 'dongwook@kyonggi.ac.kr', '이교수', '자격증', '2027-08-31', NULL, NULL, 2, '202512348', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- schedule
INSERT INTO schedule (submission_type,  content, start_date, end_date, created_at, updated_at)
VALUES ('SUBMITTED',  '학부생 졸업 논문 신청을 접수합니다.', '2025-02-24 09:00:00', '2025-03-10 18:00:00',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('MIDTHESIS',  '중간 논문 제출 및 심사 기간입니다.', '2025-04-01 09:00:00', '2025-04-12 18:00:00',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('FINALTHESIS',  '최종 논문 제출 및 심사 기간입니다.', '2025-05-20 09:00:00', '2025-06-05 18:00:00',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('CERTIFICATE',  '취득한 자격증 제출 및 심사 기간입니다.', '2025-03-15 09:00:00', '2025-03-29 18:00:00',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('APPROVED',  '최종 승인 기간입니다.', '2025-06-10 09:00:00', '2025-06-14 18:00:00',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('OTHER',  '기타 일정 입니다.', '2025-03-01 09:00:00', '2025-12-31 18:00:00',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);