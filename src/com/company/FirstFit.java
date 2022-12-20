package com.company;

import java.util.ArrayList;

public class FirstFit {

    public void apply(){
        ArrayList<Partition> partitions=Main.partitions;
        ArrayList<Process>processes=Main.processes;
        int numOfProcesses=processes.size();
        int numOfPartitions=partitions.size();
       // showPartitions(partitions);
        //showProcesses(processes);
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = 0; j < numOfPartitions; j++) {
                if(!partitions.get(j).isBusy()&&processes.get(i).getPartitionName().equals("")){
                    if(partitions.get(j).getSpace()>=processes.get(i).getSpace()){
                        double diff=partitions.get(j).getSpace()-processes.get(i).getSpace();
                        processes.get(i).setPartitionName(partitions.get(j).getName());
                        partitions.get(j).setBusy(true);
                        partitions.get(j).setProcessName(processes.get(i).getName());
                        partitions.get(j).setSpace(processes.get(i).getSpace());
                        if(diff>0){
                            Partition newPartition =new Partition("Partition"+(Main.partitionsSize++),diff,false);
                            newPartition.setExternal(true);
                           addPartition(j,newPartition);

                        }
                        break;
                    }
                }
            }
            //System.out.println("\n"+(i+1));
            //showPartitions(partitions);
            //showProcesses(processes);


        }


        Main.partitions=partitions;
        Main.processes=processes;

    }
    public void addPartition(int idx,Partition partition){
        ArrayList<Partition> partitions=Main.partitions;
        ArrayList<Process>processes=Main.processes;
        int numOfProcesses=processes.size();
        int numOfPartitions=partitions.size();
        Partition temp =new Partition("",0.0,false);
        partitions.add(temp);

        for (int i = partitions.size()-1; i >idx ; i--) {

            partitions.get(i).equal(partitions.get(i-1));
            if(partitions.get(i).getFrom().equals( partitions.get(idx).getName()))
                break;
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
