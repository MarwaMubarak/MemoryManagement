package com.company;

import java.util.ArrayList;

public class WorstFit {


    public void apply() {
        ArrayList<Partition> partitions=Main.partitions;
        ArrayList<Process>processes=Main.processes;
        int numOfProcesses=processes.size();
        int numOfPartitions=partitions.size();
        for (int i = 0; i < numOfProcesses; i++) {
//            System.out.println(i);
//            showPartitions(partitions);
//            showProcesses(processes);
//            System.out.println();
            double maxDiff = -1;
            int worstIdx = -1;
            for (int j = 0; j < numOfPartitions; j++) {
                if (!partitions.get(j).isBusy()&&processes.get(i).getPartitionName().equals("")) {
                    if (partitions.get(j).getSpace() >= processes.get(i).getSpace()) {
                        double dif = partitions.get(j).getSpace() - processes.get(i).getSpace();
                        if (maxDiff < dif) {
                            maxDiff = dif;
                            worstIdx = j;
                        }
                    }
                }
            }
            if (worstIdx != -1) {
                processes.get(i).setPartitionName(partitions.get(worstIdx).getName());
                partitions.get(worstIdx).setBusy(true);
                partitions.get(worstIdx).setProcessName(processes.get(i).getName());
                if (maxDiff > 0) {
                    Partition newPartition = new Partition("Partition" + (Main.partitionsSize++), maxDiff, false);
                    newPartition.setExternal(true);
                    addPartition(worstIdx,newPartition);
                    partitions.get(worstIdx).setSpace(processes.get(i).getSpace());


                }

            }

        }
//        System.out.println("*");
//        showPartitions(partitions);
//        showProcesses(processes);
//        System.out.println();


        Main.partitions = partitions;
        Main.processes = processes;
    }
    public void addPartition(int idx,Partition partition){
        ArrayList<Partition> partitions=Main.partitions;
        ArrayList<Process>processes=Main.processes;
        Partition temp =new Partition("",0.0,false);
        partitions.add(temp);

        for (int i = partitions.size()-1; i >=idx&&!partitions.get(i).getFrom().equals( partitions.get(idx).getName()) ; i--) {
            partitions.get(i).equal(partitions.get(i-1));
        }
        partitions.get(idx+1).equal(partition);

    }

    public static void showProcesses(ArrayList<Process>processes){
        int n=processes.size();
        for (int i = 0; i < n; i++) {
            if(processes.get(i).getPartitionName().equals(processes.get(i).getName()+" can not be allocated"))
                processes.get(i).show();
        }
    }
    public static void showPartitions(ArrayList<Partition>partitions){
        int n=partitions.size();
        for (int i = 0; i < n; i++) {
            partitions.get(i).show();
        }
    }
}
