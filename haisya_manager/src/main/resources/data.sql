/* adminsテーブル */
INSERT IGNORE INTO admins (id, username, password, role, enabled) VALUES
(1, 'haisya', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 'ROLE_ADMIN', true),
(2, 'manager', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 'ROLE_ADMIN', true);

/* teams テーブル */
INSERT IGNORE INTO teams (id, name, admin_id) VALUES
(1, 'ドルフィンズ', 1),
(2, 'コンドルズ', 2);

/* members テーブル */
INSERT IGNORE INTO members (id, name, admin_id, team_id) VALUES
(1, '鈴木 花子', 1, 1),
(2, '佐藤 美子', 1, 1),
(3, '田中 春子', 2, 2),
(4, '高橋 雪子', 2, 2);

/* children テーブル */
INSERT IGNORE INTO children (id, name, member_id) VALUES
(1, '鈴木 一郎', 1),
(2, '鈴木 次郎', 1),
(3, '佐藤 太郎', 2),
(4, '田中 三郎', 3),
(5, '高橋 四郎', 4);

/* rides テーブル */
INSERT IGNORE INTO rides (id, date, destination, memo, admin_id) VALUES
(1, '2025-05-17', '日大グラウンド', '公式戦', 1),
(2, '2025-05-18', '高瀬グラウンド', '練習試合', 1),
(3, '2025-05-25', '高瀬グラウンド', '練習試合', 2);

/* ride_entry テーブル */
INSERT IGNORE INTO ride_entry (id, ride_id, member_id, can_drive) VALUES
(1, 1, 1, true),
(2, 1, 2, false),
(3, 2, 1, false),
(4, 2, 2, true);
