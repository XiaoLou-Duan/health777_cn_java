package cn.iocoder.yudao.module.nutrition.controller.app.analysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "用户 App - 营养分析 Response VO")
@Data
public class AppNutritionAnalysisRespVO {

    @Schema(description = "分析日期", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate date;

    @Schema(description = "蛋白质目标（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "120.0")
    private BigDecimal proteinTarget;

    @Schema(description = "热量目标（千卡）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2000.0")
    private BigDecimal caloriesTarget;

    @Schema(description = "实际摄入蛋白质（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "80.5")
    private BigDecimal actualProtein;

    @Schema(description = "实际摄入热量（千卡）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1800.5")
    private BigDecimal actualCalories;

    @Schema(description = "蛋白质达标率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "67.1")
    private BigDecimal proteinAchievementRate;

    @Schema(description = "热量达标率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "90.0")
    private BigDecimal caloriesAchievementRate;

    @Schema(description = "蛋白质缺口（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "39.5")
    private BigDecimal proteinGap;

    @Schema(description = "热量缺口（千卡）", requiredMode = Schema.RequiredMode.REQUIRED, example = "199.5")
    private BigDecimal caloriesGap;

    @Schema(description = "饮食建议", example = "您今天的蛋白质摄入不足，建议增加鸡胸肉、鱼肉等高蛋白食物")
    private String dietSuggestion;

    @Schema(description = "各餐次营养摄入详情")
    private List<MealNutrition> mealNutritions;

    @Schema(description = "餐次营养摄入详情")
    @Data
    public static class MealNutrition {

        @Schema(description = "餐次类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Integer mealType;

        @Schema(description = "餐次名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "早餐")
        private String mealTypeName;

        @Schema(description = "蛋白质（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20.5")
        private BigDecimal protein;

        @Schema(description = "热量（千卡）", requiredMode = Schema.RequiredMode.REQUIRED, example = "450.5")
        private BigDecimal calories;

        @Schema(description = "食物列表", example = "米饭,鸡肉,青菜")
        private String foods;
    }

}
