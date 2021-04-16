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
public class StabilisationAnneau extends LC1_Algorithm{
	static int k = 10;
	static int n = 8;
	int sigma;
    @Override
    public String getDescription() {
        return "Stabilisation Anneau";
        
    }

    @Override
    protected void beforeStart() {
    	
		this.setLocalProperty("label", vertex.getLabel());
		this.setLocalProperty("id", this.getId());
		sigma = Math.abs(new Random().nextInt()%k);
		this.setLocalProperty("sigma", sigma);
		this.setLocalProperty("label", Character.toString((char) (65+sigma)));
		putProperty("affichageA", getLocalProperty("sigma") ,SimulationConstants.PropertyStatus.DISPLAYED);
    }

    @Override
    protected void onStarCenter() {
    	if(this.getId()==0) {
    		for(int i = 0; i < getActiveDoors().size(); i++) {
        		int numPort = getActiveDoors().get(i);
        		if((int)this.getNeighborProperty(numPort,"id") == (n-1)
        				&&(int)this.getNeighborProperty(numPort, "sigma") == sigma) {
        			sigma=(sigma+1)%k;
        			this.setLocalProperty("sigma",sigma);
        			this.setLocalProperty("label", Character.toString((char) (65+sigma)));
        		}
        	}
    	}
    	else {
    		for(int i = 0; i < getActiveDoors().size(); i++) {
        		int numPort = getActiveDoors().get(i);
        		if((int)this.getNeighborProperty(numPort,"id") == (this.getId()-1)
        				&&(int)this.getNeighborProperty(numPort, "sigma") != sigma) {
        			sigma=(int)this.getNeighborProperty(numPort, "sigma");
        			this.setLocalProperty("sigma",sigma);
        			this.setLocalProperty("label", Character.toString((char) (65+sigma)));
        		}
        	}
    	}
    	putProperty("affichageA", getLocalProperty("sigma") ,SimulationConstants.PropertyStatus.DISPLAYED);
        
        
        
    }


    @Override
    public Object clone() {
        return new StabilisationAnneau();
    }
    
   
    
   
    
}
