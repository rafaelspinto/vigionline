package vigionline.vce.system;

import java.io.File;

import vigionline.common.configuration.ConfigurationManager;

public class SystemInfo {

	private ConfigurationManager _configManager;
	private File _imageDirectory;

	public SystemInfo() {
		_configManager = ConfigurationManager.getInstance();
		_imageDirectory = new File(_configManager.getImageDirectory());
	}

	public long getFreeSpace() {

		return _imageDirectory.getUsableSpace();
	}

	public double getFreeSpaceInKB() {
		return getFreeSpace() / 1024;
	}

	public double getFreeSpaceInMB() {
		return getFreeSpaceInKB() / 1024;
	}

	public double getFreeSpaceInGB() {
		return getFreeSpaceInMB() / 1024;
	}

	public long getTotalSpace() {

		return _imageDirectory.getTotalSpace();
	}

	public double getTotalSpaceInKB() {
		return getTotalSpace() / 1024;
	}

	public double getTotalSpaceInMB() {
		return getTotalSpaceInKB() / 1024;
	}

	public double getTotalSpaceInGB() {
		return getTotalSpaceInMB() / 1024;
	}

	public double getFreePercentage() {
		return (getFreeSpace() / getTotalSpace()) * 100;
	}

	public long getAmountOfDiskSpaceExceeded() {
		double freeDiskSpacePercentageThreshold = _configManager
				.getFreeDiskSpacePercentageThreshold();
		long diskSpaceAvailable = getFreeSpace();
		long diskTotalSpace = getTotalSpace();
		long minimumDiskSpace = (long) (diskTotalSpace * (freeDiskSpacePercentageThreshold / 100));
		long differenceSpace = minimumDiskSpace - diskSpaceAvailable;
		return differenceSpace;
	}

	public boolean hasExceedDiskSpacePercentageThreshold() {
		return getAmountOfDiskSpaceExceeded() > 0;
	}
}
