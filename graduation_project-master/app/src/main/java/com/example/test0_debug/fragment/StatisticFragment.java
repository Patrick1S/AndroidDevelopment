package com.example.test0_debug.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test0_debug.R;
import com.example.test0_debug.tools.db.DBHelper;
import com.example.test0_debug.tools.db.DBStatistics;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class StatisticFragment extends Fragment {
    private static final String TAG = "myTAG";
    private Context context;
    private PieChartView pieChart;
    private boolean isCreated = false;

    private ColumnChartView columnChartView;
    private LineChartView lineChartView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isCreated) {
            initLineChart();
            initColumnChart();
            initPieChart();
            Log.d(TAG, "setUserVisibleHint: class_id  "+DBHelper.getBookClassId(context,1));
            Log.d(TAG, "setUserVisibleHint: learned "+DBStatistics.getLearnedStatistic(context));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statistic_fragment, container, false);
        pieChart = v.findViewById(R.id.pie_chart);
        columnChartView = v.findViewById(R.id.column_chart);
        lineChartView = v.findViewById(R.id.line_chart);
        initLineChart();
        initColumnChart();
        initPieChart();
        return v;
    }


    private void initPieChart() {

        PieChartData data = new PieChartData();
        int numValues = 3;   //扇形的数量
        int[] item = new int[]{DBStatistics.getLearnedStatistic(context),
                DBStatistics.getRestWordNum(context),
                DBStatistics.getTotalWordNum(context) - DBStatistics.getLearnedStatistic(context)};
        //已学，将学，未学
        int[] color = new int[]{getResources().getColor(R.color.pie_color2),
                getResources().getColor(R.color.pie_color3),
                getResources().getColor(R.color.pie_color1)};
        //存放扇形数据的集合
        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue(item[i], color[i]);
            values.add(sliceValue);

        }
        data.setValues(values);
        data.setHasLabels(true);
        data.setCenterText1(DBStatistics.getProgressStatistics(context) + "%");
        data.setCenterText1Color(Color.GRAY);
        data.setHasCenterCircle(true);
        data.setSlicesSpacing(24);
        data.setCenterText2(DBHelper.getTitle(context, 1));
        data.setCenterText2Color(Color.GRAY);
        pieChart.setPieChartData(data);

    }

    private void initColumnChart() {

        final String[] cognition = new String[]{"熟记", "认识", "模糊", "遗忘", "收藏", "今日剩余"};
        int[] color = new int[]{Color.parseColor("#298A08"),
                Color.parseColor("#01DFA5"),
                Color.parseColor("#D7DF01"),
                Color.parseColor("#FA5858"),
                Color.parseColor("#673AB7"),
                Color.GRAY
        };
        int[] score = {DBStatistics.getMemorizingStatistic(context),
                DBStatistics.getKnowingStatistic(context),
                DBStatistics.getVaguenessStatistic(context),
                DBStatistics.getForgettingStatistic(context),
                DBStatistics.getFavoritesWordNum(context),
                DBStatistics.getRestWordNum(context)

        };

        ColumnChartData columnData;
        //初始化数据并显示在图表上
        List<AxisValue> axisValues = new ArrayList<>();
        List<Column> columns = new ArrayList<>();
        for (int i = 0; i < cognition.length; ++i) {
            axisValues.add(new AxisValue(i).setLabel(cognition[i]));
            List<SubcolumnValue> values = new ArrayList<>();
            SubcolumnValue subcolumnValue = new SubcolumnValue();//子柱
            subcolumnValue.setValue(score[i]);
            subcolumnValue.setColor(color[i]);
            values.add(subcolumnValue);
            Column column = new Column();
            column.setValues(values);
            column.setHasLabels(true);
            columns.add(column);
        }
        columnData = new ColumnChartData(columns);
        Axis axisX = new Axis();
        axisX.setValues(axisValues);
        axisX.setHasLines(true);
        axisX.setTextColor(getResources().getColor(R.color.black));
        axisX.setName("熟知度");
        columnData.setAxisXBottom(axisX);//x在底部
        Axis axisY = new Axis();
        axisY.setHasLines(true);
        axisY.setName("单词数量");
        axisY.setTextColor(getResources().getColor(R.color.black));
        columnData.setAxisYLeft(axisY);
        columnChartView.setColumnChartData(columnData);
        columnChartView.setValueSelectionEnabled(true);
        columnChartView.setZoomType(ZoomType.HORIZONTAL);
        columnChartView.setInteractive(true);//可滑动
    }

    private void initLineChart() {

        String[] date = {"今天", "明天", "后天", "4天", "5天", "6天", "7天", "8天", "9天", "10天",
                "11天", "12天", "13天", "14天", "15天", "16天", "17天", "18天", "19天", "20天", "21天"
                , "一个月", "2个月", "3个月", "6个月", "一年",};//X轴的标注
        float[] score = {100, 33.7f, 27.8f, 26, 25, 25, 25, 24, 24, 24,
                24, 24, 23, 23, 23, 23, 23, 23, 23, 22,
                22, 21, 17, 16, 13, 5};//图表的数据
        List<PointValue> mPointValues = new ArrayList<PointValue>();
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
            mPointValues.add(new PointValue(i, score[i]));
        }//获取坐标点  图表的每个点的显示
        Line line = new Line(mPointValues).setColor(getResources().getColor(R.color.line_color));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
        line.setStrokeWidth(2);//线条的粗细，默认是3
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
//	    axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setTextColor(getResources().getColor(R.color.black));//灰色
//	    axisX.setName("未来几天的天气");  //表格名称
        axisX.setName("时间");
        axisX.setTextSize(11);//设置字体大小
        // axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//	    data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线
        Axis axisY = new Axis();  //Y轴
        axisY.setName("遗忘率%");//y轴标注
        axisY.setTextColor(getResources().getColor(R.color.black));
        axisY.setTextSize(11);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
        lineChartView.setMaxZoom((float) 3);//缩放比例
        lineChartView.setLineChartData(data);
        lineChartView.setVisibility(View.VISIBLE);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.left = 0;
        viewport.right = 7;
        lineChartView.setCurrentViewport(viewport);
    }


}