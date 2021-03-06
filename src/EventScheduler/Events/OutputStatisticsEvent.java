package EventScheduler.Events;

import Statistics.FamilyStatisticsTuple;
import SwingElements.Base;
import java.util.ArrayList;

public class OutputStatisticsEvent extends Event {

    public OutputStatisticsEvent(int stepTargetIn, String eventNameIn, boolean repeatIn, Base in) {
        super(stepTargetIn, eventNameIn, repeatIn, in);
    }//end constructor

    @Override
    public void takeAction() {
        int totalLines = 0;
        ArrayList<FamilyStatisticsTuple> familyStatisticsHolder = new ArrayList<>();
        for (int i = 1; i <= ref.getGraph().getFamilyCount(); i++) {
            familyStatisticsHolder.add(new FamilyStatisticsTuple(ref.getGraph().pullFamily(i), i, ref.getGraph()));
            totalLines += familyStatisticsHolder.get(i - 1).familyMembers.size();
        }//end for
        for (int i=0;i<familyStatisticsHolder.size();i++) {
            FamilyStatisticsTuple fst = familyStatisticsHolder.get(i);
            fst.proportionOfTotalLines = (double) fst.familyMembers.size() / totalLines;
            int recordID = (int) (ref.getGraph().getStepCount() / 50);
            String updateOut = "FamilyID: " + fst.familyID +
                    " # of members: " +
                    fst.familyMembers.size() +
                    " Family population proportion: " +
                    fst.proportionOfTotalLines +
                    " Average lifespan: " +
                    fst.averageLifespan +
                    " Lifespan variation: " +
                    fst.lifespanVariation +
                    " Lifespan deviation: " +
                    fst.lifespanDeviation +
                    " Mean deviation: " +
                    fst.meanDeviation;
            updateOut = updateOut.replace("NaN", "0.0");
            System.out.println(updateOut);
        }//end for
    }//end takeAction
}//end OutputStatisticsEventClass
