package org.shelk.customsrunnables;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.utils.MathParticles;

public class OtherParticlesRunnable extends BukkitRunnable{

	public OtherParticlesRunnable() {
		
	}
	
	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (ItemParticles.getParticleEffectItem(p) != null && ItemParticles.getParticleEffectItem(p).size() != 0) {
				    for(ParticleEffect pe : ItemParticles.getParticleEffectItem(p)) {
				    	
				    float rgb1 = MathParticles.getRGB(1, pe);
				    float rgb2 = MathParticles.getRGB(2, pe);
				    float rgb3 = MathParticles.getRGB(3, pe);
				   
				    // RGB SET 
				  
				    World world = p.getWorld();
				    
					// PLAY CIRCLE
					if (pe.getShape().equals("circle")) {
				        MathParticles.playCircle(p, p.getLocation(), pe, 2, rgb1, rgb2, rgb3);
				     }
					
					// PLAY SPHERE
					
					if (pe.getShape().equals("sphere")) {
						Location location = p.getLocation().add(0,3.5,0);
						for (double i = 0; i <= Math.PI; i += Math.PI / 6) {
							  double radius = Math.sin(i); 
							  double y = Math.cos(i); 
							
						for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
							  double x = Math.cos(a) * radius; 
							  double z = Math.sin(a) * radius;
							  location.add(x, y, z);
							  MathParticles.spawnParticle(world, pe, location, rgb1, rgb2, rgb3, p);
							  location.subtract(x, y, z);
							}
					   }
						
					}	
					// PLAY DISK
					
					if (pe.getShape().equals("disk")) {
						double radius = 0.25;
						for(int i = 0;i<8;i++) {
							MathParticles.playCircle(p, p.getLocation(), pe, radius, rgb1, rgb2, rgb3);
							radius += 0.25;
						}
				   }
					if (pe.getShape().equals("star")) {
						Location loc = p.getLocation();
						
						for (int iteration = 0; iteration < 5; iteration++) {
							  double angle = 360.0 / 5 * iteration;
							  double nextAngle = 360.0 / 5 * (iteration + 2);
							  angle = Math.toRadians(angle);
							  nextAngle = Math.toRadians(nextAngle);
							  double x = Math.cos(angle) *3.5;
							  double z = Math.sin(angle) *3.5;
							  double x2 = Math.cos(nextAngle) *3.5;
							  double z2 = Math.sin(nextAngle) *3.5;
							  double deltaX = x2 - x;
							  double deltaZ = z2 - z;
							  double distance = Math.sqrt((deltaX - x) * (deltaX - x) + (deltaZ - z) * (deltaZ - z)) * 0.11;
							  for (double d = 0; d < distance - .1; d += .02) {
							    loc.add(x + deltaX * d, 0, z + deltaZ * d);
							    MathParticles.spawnParticle(p.getWorld(), pe, loc, rgb1, rgb2, rgb3, p);
							    loc.subtract(x + deltaX * d, 0, z + deltaZ * d);
							  }
							}
							 
						}
						
					}
				
					
					
			  }
					
			}
		}
	}	

