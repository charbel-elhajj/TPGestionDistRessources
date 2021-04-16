import java.util.ArrayList;
import java.util.Random;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

/**
 *
 * @author Charbel
 */
public class PerteInfo extends LC1_Algorithm{
	int neighborIds[];
	String neighborLabels[];
	static int synchCount = 7;
	int neighborCount;
	boolean errorFound = false;
    @Override
    public String getDescription() {
        return "Perte info";
        
    }

    @Override
    protected void beforeStart() {
    	
		this.setLocalProperty("label", vertex.getLabel());
		this.setLocalProperty("id", this.getId());
		this.setLocalProperty("missingId", -1);
		this.setLocalProperty("missingLabel", " ");
		neighborIds = new int[vertex.getDegree()];
		neighborLabels = new String[vertex.getDegree()];
		neighborIds[0] = -1;
		neighborLabels[0] = " ";
		neighborCount = vertex.getDegree();
		putProperty("affichageA", this.getLocalProperty("missingLabel") ,SimulationConstants.PropertyStatus.DISPLAYED);
    }

    @Override
    protected void onStarCenter() {
    	for(int i = 0; i < getActiveDoors().size(); i++) {
    		int numPort = getActiveDoors().get(i);
    		if(numPort != 0) {
    			neighborIds[numPort] = (int)this.getNeighborProperty(numPort,"id");
    			neighborLabels[numPort] = this.getNeighborProperty(numPort,"label").toString();
    		}
    	}
    	if(this.getLocalProperty("label").equals("A")) {
    		if(synchCount == 0) {
    			this.localTermination();
    		}
    		else {
    			synchCount--;
    		}
    	}
    	else {
    		if(neighborCount>getActiveDoors().size()) {
    			int index = 0;
    			for(;index<neighborCount;index++) {
    				if(!getActiveDoors().contains(index)) {
    					break;
    				}
    			}

    			if(index!=0) {
    				this.setLocalProperty("missingId", neighborIds[index]);
        			this.setLocalProperty("missingLabel", neighborLabels[index]);
        			errorFound = true;
    			}
    			else {
    				for(int i = 0; i < getActiveDoors().size() && !errorFound; i++) {
                		int numPort = getActiveDoors().get(i);
                		if(!this.getNeighborProperty(numPort, "missingId").equals(this.getLocalProperty("missingId"))) {
                			this.setLocalProperty("missingId",this.getNeighborProperty(numPort, "missingId"));
                			this.setLocalProperty("missingLabel",this.getNeighborProperty(numPort, "missingLabel"));
                			errorFound = true;
                			neighborIds[0] = (int)this.getNeighborProperty(numPort, "missingId");
                			neighborLabels[0] = this.getNeighborProperty(numPort, "missingLabel").toString();
                			
                		}
                	}
    			}
    			
    			
    		}
    		else if(!errorFound) {
        		for(int i = 0; i < getActiveDoors().size(); i++) {
            		int numPort = getActiveDoors().get(i);
            		if(!this.getNeighborProperty(numPort, "missingId").equals(-1)) {
            			this.setLocalProperty("missingId",this.getNeighborProperty(numPort, "missingId"));
            			this.setLocalProperty("missingLabel",this.getNeighborProperty(numPort, "missingLabel"));
            			errorFound = true;
            		}
            	}
        	}
    	}
    	
    	putProperty("affichageA", this.getLocalProperty("missingLabel") ,SimulationConstants.PropertyStatus.DISPLAYED);
        
        
    }


    @Override
    public Object clone() {
        return new PerteInfo();
    }
    
   
    
   
    
}
