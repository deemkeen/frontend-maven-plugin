package com.github.eirslett.maven.plugins.frontend.lib;

import java.io.File;

public interface BunExecutorConfig {

    File getNodePath();

    File getBunPath();

    File getWorkingDirectory();

    Platform getPlatform();
}

final class InstallBunExecutorConfig implements BunExecutorConfig {
    
    public static final String BUN_WINDOWS = BunInstaller.INSTALL_PATH + ".exe";

    private File nodePath;

    private final InstallConfig installConfig;

    public InstallBunExecutorConfig(InstallConfig installConfig) {
        this.installConfig = installConfig;
        nodePath = new InstallNodeExecutorConfig(installConfig).getNodePath();
    }

    @Override
    public File getNodePath() {
        return nodePath;
    }

    @Override
    public File getBunPath() {
        String bunExecutable = getPlatform().isWindows() ? BUN_WINDOWS : BunInstaller.INSTALL_PATH;
        return new File(installConfig.getInstallDirectory() + bunExecutable);
    }

    @Override
    public File getWorkingDirectory() {
        return installConfig.getWorkingDirectory();
    }

    @Override
    public Platform getPlatform() {
        return installConfig.getPlatform();
    }
}