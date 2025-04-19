-- 管理者ユーザー
INSERT INTO admin (username, password, role) VALUES 
('haisya', 'password', 'ADMIN');

-- メンバー（保護者）
INSERT INTO members (name) VALUES 
('矢部帆南美'),
('大谷真美子');

-- 子ども
INSERT INTO children (name, member_id) VALUES 
('矢部睦大', 1),
('矢部日々人', 1),
('大谷翔真', 2);

-- 配車イベント（乗り合いの予定）
INSERT INTO rides (date, destination, memo, created_by_id) VALUES 
('2025-04-30', '日大グラウンド', '公式戦', 1);

-- 配車エントリー
INSERT INTO ride_entry (ride_id, member_id, can_drive) VALUES 
(1, 1, true),
(1, 2, false);