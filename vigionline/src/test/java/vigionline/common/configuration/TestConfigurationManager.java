package vigionline.common.configuration;

import junit.framework.Assert;

import org.junit.Test;

public class TestConfigurationManager {
	
	@Test
	public void testDatabase()
	{
		ConfigurationManager cfg = ConfigurationManager.getInstance();
		Assert.assertEquals("vigionline",cfg.getString("database"));
		Assert.assertEquals(3306,cfg.getInt("port"));
		Assert.assertEquals("root",cfg.getString("user"));
		Assert.assertEquals("Trsp1981",cfg.getString("password"));
	}
}
