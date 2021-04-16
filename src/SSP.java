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
public class SSP extends LC1_Algorithm{
	boolean p;

	int synchCount;
    @Override
    public String getDescription() {
        return "SSP";
        
    }

    @Override
    protected void beforeStart() {
    	synchCount = new Random().nextInt()%4+2;
    	p = false;
        this.setLocalProperty("label", vertex.getLabel());
        this.setLocalProperty("a", -1);
        putProperty("affichageA", getLocalProperty("a") ,SimulationConstants.PropertyStatus.DISPLAYED);
        
    }

    @Override
    protected void onStarCenter() {
    	if(synchCount>0) {
    		synchCount--;
    	}
    	else if(!p){
    		this.setLocalProperty("label","U");
    		p = true;
    	}
    	if(p && (int)this.getLocalProperty("a")<5) {
    		int min = (int)this.getLocalProperty("a");
    		for(int i = 0; i < getActiveDoors().size(); i++) {
        		int numPort = getActiveDoors().get(i);
        		int temp = (int)this.getNeighborProperty(numPort, "a");
        		if(temp<min) {
        			min = temp;
        		}
    		}
    		min++;
    		this.setLocalProperty("a", min);
    	}
    	else if(p && (int)this.getLocalProperty("a")>=5) {
    		//this.globalTermination();;
    		this.localTermination();;
    	}
    	
    	putProperty("affichageA", getLocalProperty("a") ,SimulationConstants.PropertyStatus.DISPLAYED);
        
    }


    @Override
    public Object clone() {
        return new SSP();
    }
    
   
    
   
    
}
