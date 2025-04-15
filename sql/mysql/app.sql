-- ------------------------------------
-- 数据库: sarcopenia_app (示例名称)
-- ------------------------------------
-- CREATE DATABASE IF NOT EXISTS sarcopenia_app DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- USE sarcopenia_app;

-- ------------------------------------
-- 模块: 用户管理 & 基础 (User Management & Core)
-- ------------------------------------

-- 用户表 (users)
CREATE TABLE `users` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `mobile` VARCHAR(15) NOT NULL UNIQUE COMMENT '手机号码 (唯一, 登录凭证)',
    `password_hash` VARCHAR(255) NULL COMMENT '密码哈希值 (手机+密码登录时使用)',
    `nickname` VARCHAR(50) NULL COMMENT '用户昵称',
    `avatar_url` VARCHAR(255) NULL COMMENT '用户头像URL',
    `gender` TINYINT NULL COMMENT '性别 (0: 未知, 1: 男, 2: 女)',
    `birth_date` DATE NULL COMMENT '出生日期',
    `role` INT NOT NULL DEFAULT 'patient' COMMENT '用户角色 1:patient, 2:doctor, 3:admin',
    `protein_target` DECIMAL(10, 2) NULL COMMENT '每日蛋白质目标 (克) - 患者适用',
    `calories_target` DECIMAL(10, 2) NULL COMMENT '每日热量目标 (千卡) - 患者适用',
    `total_points` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '累计积分',
    `is_active` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '账户是否激活/可用',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `last_login_at` TIMESTAMP NULL COMMENT '最后登录时间',
    PRIMARY KEY (`id`),
    INDEX `idx_mobile` (`mobile`),
    INDEX `idx_role` (`role`),
    INDEX `idx_total_points` (`total_points`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息及登录凭证表';

-- 密码重置令牌表 (password_reset_tokens)
CREATE TABLE `password_reset_tokens` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL COMMENT '关联的用户ID',
    `token` VARCHAR(255) NOT NULL UNIQUE COMMENT '重置令牌 (哈希存储)',
    `expires_at` TIMESTAMP NOT NULL COMMENT '令牌过期时间',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_expires_at` (`expires_at`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '密码重置令牌';

-- 应用设置表 (app_settings) - 可选
CREATE TABLE `app_settings` (
    `setting_key` VARCHAR(50) NOT NULL UNIQUE COMMENT '设置键名',
    `setting_value` TEXT NOT NULL COMMENT '设置值',
    `description` VARCHAR(255) NULL COMMENT '设置描述',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`setting_key`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '全局应用配置表';

-- ------------------------------------
-- 模块: 营养管理 (Nutrition Management)
-- ------------------------------------

-- 饮食日志表 (diet_logs)
CREATE TABLE `diet_logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '饮食日志ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID (逻辑关联 users.id)',
    `meal_type` TINYINT NOT NULL COMMENT '餐次类型 (1:早餐, 2:午餐, 3:晚餐, 4:加餐)',
    `log_time` DATETIME NOT NULL COMMENT '记录时间 (用户实际用餐时间)',
    `photo_url` VARCHAR(255) NULL COMMENT '食物照片URL',
    `recognized_foods` TEXT NULL COMMENT 'AI识别出的食物文本描述或逗号分隔列表',
    `estimated_protein` DECIMAL(10, 2) NULL COMMENT '估算蛋白质 (克)',
    `estimated_calories` DECIMAL(10, 2) NULL COMMENT '估算热量 (千卡)',
    `user_notes` TEXT NULL COMMENT '用户备注',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_logtime` (`user_id`, `log_time`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户饮食日志表';

-- 乳清蛋白摄入记录表 (whey_protein_logs)
CREATE TABLE `whey_protein_logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '乳清蛋白日志ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID (逻辑关联 users.id)',
    `intake_time` DATETIME NOT NULL COMMENT '摄入时间',
    `amount_grams` DECIMAL(5, 1) NOT NULL DEFAULT 15.0 COMMENT '摄入量 (克)',
    `confirmation_photo_url` VARCHAR(255) NULL COMMENT '确认照片URL',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_intaketime` (`user_id`, `intake_time`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '乳清蛋白摄入记录表';

-- AI识别日志表 (ai_recognition_logs) - 可选，用于审计和调试
CREATE TABLE `ai_recognition_logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `diet_log_id` BIGINT UNSIGNED NULL COMMENT '关联的饮食日志ID (逻辑关联 diet_logs.id)',
    `request_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求发送时间',
    `request_payload` TEXT NULL COMMENT '请求内容摘要或ID',
    `response_timestamp` TIMESTAMP NULL COMMENT '收到响应时间',
    `response_payload` TEXT NULL COMMENT '响应内容摘要或结果',
    `status_code` INT NULL COMMENT '外部API响应状态码',
    `success` BOOLEAN NULL COMMENT '调用是否成功',
    `error_message` TEXT NULL COMMENT '错误信息',
    PRIMARY KEY (`id`),
    INDEX `idx_diet_log_id` (`diet_log_id`),
    INDEX `idx_request_timestamp` (`request_timestamp`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '调用外部AI食物识别服务的日志';

-- ------------------------------------
-- 模块: 运动管理 (Exercise Management)
-- ------------------------------------

-- 运动视频表 (exercise_videos)
CREATE TABLE `exercise_videos` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '视频ID',
    `title` VARCHAR(100) NOT NULL COMMENT '视频标题',
    `description` TEXT NULL COMMENT '视频描述',
    `video_url` VARCHAR(255) NOT NULL COMMENT '视频播放地址',
    `cover_image_url` VARCHAR(255) NULL COMMENT '视频封面图URL',
    `difficulty_level` TINYINT NOT NULL COMMENT '难度级别 (1:初级, 2:中级, 3:高级)',
    `estimated_duration_minutes` INT UNSIGNED NULL COMMENT '预计时长 (分钟)',
    `is_active` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否启用',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_difficulty_level` (`difficulty_level`),
    INDEX `idx_is_active` (`is_active`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '运动指导视频表';

-- 运动日志表 (exercise_logs)
CREATE TABLE `exercise_logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '运动日志ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID (逻辑关联 users.id)',
    `video_id` INT UNSIGNED NULL COMMENT '关联的运动视频ID (逻辑关联 exercise_videos.id)',
    `exercise_description` VARCHAR(255) NULL COMMENT '运动描述 (如果非视频运动)',
    `start_time` DATETIME NOT NULL COMMENT '运动开始时间',
    `duration_minutes` INT UNSIGNED NOT NULL COMMENT '实际运动时长 (分钟)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_starttime` (`user_id`, `start_time`),
    INDEX `idx_video_id` (`video_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户运动日志表';

-- ------------------------------------
-- 模块: 激励系统 (Gamification)
-- ------------------------------------

-- 积分记录表 (points_logs)
CREATE TABLE `points_logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '积分记录ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID (逻辑关联 users.id)',
    `points_earned` INT NOT NULL COMMENT '获得的积分数 (可为负数)',
    `source_type` VARCHAR(50) NOT NULL COMMENT '积分来源类型 (如: DIET_LOG, EXERCISE_LOG, WHEY_LOG, COMMUNITY_POST, ACHIEVEMENT)',
    `source_id` BIGINT UNSIGNED NULL COMMENT '来源关联ID (如饮食日志ID, 帖子ID等)',
    `description` VARCHAR(255) NULL COMMENT '积分变更说明',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_createdat` (`user_id`, `created_at`),
    INDEX `idx_source_type_id` (`source_type`, `source_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户积分变更记录表';

-- 成就/勋章定义表 (achievements)
CREATE TABLE `achievements` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '成就ID',
    `name` VARCHAR(50) NOT NULL UNIQUE COMMENT '成就名称 (如: 健康达人)',
    `description` VARCHAR(255) NOT NULL COMMENT '达成条件描述',
    `icon_url` VARCHAR(255) NULL COMMENT '成就图标URL',
    `trigger_condition` VARCHAR(255) NULL COMMENT '系统内部触发条件标识 (可选)',
    `points_reward` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '达成奖励积分',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_name` (`name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '成就或勋章定义表';

-- 用户获取成就记录表 (user_achievements)
CREATE TABLE `user_achievements` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID (逻辑关联 users.id)',
    `achievement_id` INT UNSIGNED NOT NULL COMMENT '成就ID (逻辑关联 achievements.id)',
    `earned_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获得时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_user_achievement` (`user_id`, `achievement_id`), -- 防止重复获取同一成就
    INDEX `idx_achievement_id` (`achievement_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户获得的成就记录';

-- ------------------------------------
-- 模块: 社区互动 (Community)
-- ------------------------------------

-- 话题分类表 (community_topics)
CREATE TABLE `community_topics` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '话题ID',
    `name` VARCHAR(50) NOT NULL UNIQUE COMMENT '话题名称 (如: 饮食分享)',
    `description` VARCHAR(255) NULL COMMENT '话题描述',
    `sort_order` INT NOT NULL DEFAULT 0 COMMENT '排序值',
    `is_active` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否启用',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_is_active_sort` (`is_active`, `sort_order`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '社区话题分类表';

-- 社区帖子表 (community_posts)
CREATE TABLE `community_posts` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '发帖用户ID (逻辑关联 users.id)',
    `topic_id` INT UNSIGNED NOT NULL COMMENT '所属话题ID (逻辑关联 community_topics.id)',
    `title` VARCHAR(100) NULL COMMENT '帖子标题 (可选)',
    `content` TEXT NOT NULL COMMENT '帖子内容',
    `like_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数 (冗余字段, 需维护)',
    `comment_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论数 (冗余字段, 需维护)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态 (0:待审核, 1:已发布, 2:已隐藏)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_createdat` (`user_id`, `created_at`),
    INDEX `idx_topic_status_createdat` (
        `topic_id`,
        `status`,
        `created_at`
    ),
    INDEX `idx_status_createdat` (`status`, `created_at`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '社区帖子表';

-- 社区帖子图片表 (community_post_images)
CREATE TABLE `community_post_images` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `post_id` BIGINT UNSIGNED NOT NULL COMMENT '关联的帖子ID (逻辑关联 community_posts.id)',
    `image_url` VARCHAR(255) NOT NULL COMMENT '图片存储URL',
    `sort_order` TINYINT NOT NULL DEFAULT 0 COMMENT '图片排序',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_post_id` (`post_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '社区帖子包含的图片';

-- 帖子评论表 (community_comments)
CREATE TABLE `community_comments` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `post_id` BIGINT UNSIGNED NOT NULL COMMENT '关联的帖子ID (逻辑关联 community_posts.id)',
    `user_id` INT UNSIGNED NOT NULL COMMENT '评论用户ID (逻辑关联 users.id)',
    `parent_comment_id` BIGINT UNSIGNED NULL COMMENT '父评论ID (逻辑关联 community_comments.id)',
    `content` TEXT NOT NULL COMMENT '评论内容',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态 (0:待审核, 1:已发布, 2:已隐藏)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
    PRIMARY KEY (`id`),
    INDEX `idx_post_status_createdat` (
        `post_id`,
        `status`,
        `created_at`
    ),
    INDEX `idx_user_createdat` (`user_id`, `created_at`),
    INDEX `idx_parent_comment_id` (`parent_comment_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子评论表';

-- 帖子点赞表 (community_likes)
CREATE TABLE `community_likes` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '点赞记录ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '点赞用户ID (逻辑关联 users.id)',
    `post_id` BIGINT UNSIGNED NOT NULL COMMENT '被点赞的帖子ID (逻辑关联 community_posts.id)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_user_post` (`user_id`, `post_id`), -- 确保唯一性
    INDEX `idx_post_id` (`post_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子点赞记录表';

-- ------------------------------------
-- 模块: 医患沟通 (Doctor Interaction)
-- ------------------------------------

-- 医生信息表 (doctors)
CREATE TABLE `doctors` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '医生档案ID',
    `user_id` INT UNSIGNED NOT NULL UNIQUE COMMENT '关联的用户ID (逻辑关联 users.id, 且该 user 的 role 需为 doctor)',
    `title` VARCHAR(50) NULL COMMENT '职称 (如: 主任医师)',
    `specialty` VARCHAR(100) NULL COMMENT '专长领域 (如: 老年医学, 营养学)',
    `hospital` VARCHAR(100) NULL COMMENT '所属医院',
    `bio` TEXT NULL COMMENT '医生简介',
    `is_active` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否可提供咨询 (由管理员或医生自己控制)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '档案创建时间',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '档案更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_is_active` (`is_active`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '医生档案信息表 (关联users表获取基础信息和登录)';

-- 患者提问表 (patient_questions)
CREATE TABLE `patient_questions` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '问题ID',
    `patient_user_id` INT UNSIGNED NOT NULL COMMENT '提问患者的用户ID (逻辑关联 users.id)',
    `assigned_doctor_user_id` INT UNSIGNED NULL COMMENT '指定回答的医生用户ID (逻辑关联 users.id)',
    `question_text` TEXT NULL COMMENT '问题文字内容',
    `question_voice_url` VARCHAR(255) NULL COMMENT '问题语音文件URL',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态 (0:待回答, 1:已回答, 2:已关闭)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提问时间',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_patient_status_createdat` (
        `patient_user_id`,
        `status`,
        `created_at`
    ),
    INDEX `idx_doctor_status_createdat` (
        `assigned_doctor_user_id`,
        `status`,
        `created_at`
    ),
    INDEX `idx_status_createdat` (`status`, `created_at`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '患者在线提问表';

-- 医生回答表 (doctor_answers)
CREATE TABLE `doctor_answers` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '回答ID',
    `question_id` BIGINT UNSIGNED NOT NULL COMMENT '关联的问题ID (逻辑关联 patient_questions.id)',
    `doctor_user_id` INT UNSIGNED NOT NULL COMMENT '回答医生的用户ID (逻辑关联 users.id)',
    `answer_text` TEXT NULL COMMENT '回答文字内容',
    `answer_voice_url` VARCHAR(255) NULL COMMENT '回答语音文件URL',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回答时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_question_id` (`question_id`), -- 假设一个问题只有一个最终回答
    INDEX `idx_doctor_user_id` (`doctor_user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '医生回答表';

-- ------------------------------------
-- 模块: 智能提醒 & 通知 (Reminders & Notifications)
-- ------------------------------------

-- 用户提醒设置表 (user_reminder_settings)
CREATE TABLE `user_reminder_settings` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '设置ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID (逻辑关联 users.id)',
    `reminder_type` VARCHAR(20) NOT NULL COMMENT '提醒类型 (DIET, EXERCISE, WHEY_MORNING, WHEY_AFTERNOON)',
    `reminder_time` TIME NULL COMMENT '提醒时间 (HH:MM:SS) - 用户自定义时间',
    `is_enabled` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否启用该提醒',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_user_type` (`user_id`, `reminder_type`),
    INDEX `idx_is_enabled_time` (`is_enabled`, `reminder_time`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户自定义提醒设置';

-- 通知记录表 (notifications)
CREATE TABLE `notifications` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL COMMENT '接收通知的用户ID (逻辑关联 users.id)',
    `notification_type` VARCHAR(50) NOT NULL COMMENT '通知类型 (如: REMINDER_DIET, NEW_ANSWER, NEW_COMMENT, ACHIEVEMENT_UNLOCKED)',
    `title` VARCHAR(100) NULL COMMENT '通知标题',
    `content` TEXT NOT NULL COMMENT '通知内容',
    `related_entity_type` VARCHAR(50) NULL COMMENT '关联实体类型 (如: question, post, achievement)',
    `related_entity_id` BIGINT UNSIGNED NULL COMMENT '关联实体ID',
    `status` INT NOT NULL DEFAULT 'pending' COMMENT '通知状态，1:pending, 2:sent, 3:read, 4:deleted, 5:failed',
    `scheduled_at` TIMESTAMP NULL COMMENT '预定发送时间 (用于延迟通知)',
    `sent_at` TIMESTAMP NULL COMMENT '实际发送时间',
    `read_at` TIMESTAMP NULL COMMENT '用户读取时间',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_user_status_createdat` (
        `user_id`,
        `status`,
        `created_at`
    ),
    INDEX `idx_status_scheduledat` (`status`, `scheduled_at`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT '系统通知发送记录';