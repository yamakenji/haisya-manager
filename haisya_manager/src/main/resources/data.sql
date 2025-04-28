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
(1, '鈴木', 1, 1),
(2, '佐藤', 1, 1),
(3, '田中', 1, 1),
(4, '寺田', 1, 1),
(5, '今宮', 2, 2),
(6, '小久保', 2, 2),
(7, '松井', 2, 2);

/* children テーブル */
INSERT IGNORE INTO children (id, name, member_id) VALUES
(1, '鈴木 一郎', 1),
(2, '鈴木 次郎', 1),
(3, '佐藤 太郎', 2),
(4, '田中 三郎', 3),
(5, '寺田 一郎', 4),
(6, '寺田 次郎', 4),
(7, '寺田 太郎', 4),
(8, '今宮 三郎', 5),
(9, '今宮 一郎', 5),
(10, '小久保 次郎', 6),
(11, '小久保 太郎', 6),
(12, '松井 三郎', 7),
(13, '松井 四郎', 7);

/* rides テーブル */
INSERT IGNORE INTO rides (id, date, destination, memo, admin_id) VALUES
(1, '2025-05-17', '日大グラウンド', '公式戦', 1),
(2, '2025-05-18', '高瀬グラウンド', '練習試合', 1),
(3, '2025-05-25', '高瀬グラウンド', '練習試合', 2);

/* ride_entry （行けるか行けないかを保存する）テーブル　*/
INSERT IGNORE INTO ride_entry (id, ride_id, member_id, can_drive) VALUES
(1, 1, 1, true),
(2, 1, 2, false),
(3, 1, 3, true),
(4, 1, 4, true),
(5, 2, 1, true),
(6, 2, 2, true),
(7, 2, 3, true),
(8, 2, 4, true),
(9, 3, 5, true),
(10, 3, 6, false),
(11, 3, 7, true);

/* ride_member_entry　（行ける人の中で実際、配車に指定された人を保存する） テーブル */
INSERT IGNORE INTO ride_member_entry (id, ride_id, member_id) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 1, 4),
(4, 2, 1),
(5, 2, 2),
(6, 2, 3),
(7, 2, 4),
(8, 3, 5),
(9, 3, 7);

/* ride_child_entry　（配車に指定された人が乗せる子供を保存する） テーブル */
INSERT IGNORE INTO ride_child_entry (id, ride_id, child_id, ride_member_id) VALUES
(1, 1, 1, 1),
(2, 1, 2, 3),
(3, 1, 3, 2),
(4, 1, 4, 2),
(5, 1, 5, 3),
(6, 1, 6, 1),
(7, 1, 7, 1),
(8, 2, 1, 4),
(9, 2, 2, 7),
(10, 2, 3, 6),
(11, 2, 4, 7),
(12, 2, 5, 5),
(13, 2, 6, 5),
(14, 2, 7, 4),
(15, 3, 8, 9),
(16, 3, 9, 8),
(17, 3, 10, 7),
(18, 3, 11, 8),
(19, 3, 12, 9),
(20, 3, 13, 7);