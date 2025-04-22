-- 管理者ユーザー
INSERT INTO admin (username, password, role, enabled)
SELECT 'haisya', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 'ROLE_ADMIN', true
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE username = 'haisya');

-- メンバー（保護者）
INSERT INTO members (name)
SELECT '佐藤花子' WHERE NOT EXISTS (SELECT 1 FROM members WHERE name = '佐藤花子');

INSERT INTO members (name)
SELECT '鈴木美子' WHERE NOT EXISTS (SELECT 1 FROM members WHERE name = '鈴木美子');

-- 子ども
INSERT INTO children (name, member_id)
SELECT '佐藤一郎', 1 WHERE NOT EXISTS (SELECT 1 FROM children WHERE name = '佐藤一郎' AND member_id = 1);

INSERT INTO children (name, member_id)
SELECT '佐藤次郎', 1 WHERE NOT EXISTS (SELECT 1 FROM children WHERE name = '佐藤次郎' AND member_id = 1);

INSERT INTO children (name, member_id)
SELECT '鈴木太郎', 2 WHERE NOT EXISTS (SELECT 1 FROM children WHERE name = '鈴木太郎' AND member_id = 2);

-- 配車イベント（乗り合いの予定）
INSERT INTO rides (date, destination, memo, created_by_id)
SELECT '2025-04-30', '日大グラウンド', '公式戦', 1
WHERE NOT EXISTS (SELECT 1 FROM rides WHERE date = '2025-04-30' AND destination = '日大グラウンド');

-- 配車エントリー
INSERT INTO ride_entry (ride_id, member_id, can_drive)
SELECT 1, 1, true
WHERE NOT EXISTS (SELECT 1 FROM ride_entry WHERE ride_id = 1 AND member_id = 1);

INSERT INTO ride_entry (ride_id, member_id, can_drive)
SELECT 1, 2, false
WHERE NOT EXISTS (SELECT 1 FROM ride_entry WHERE ride_id = 1 AND member_id = 2);