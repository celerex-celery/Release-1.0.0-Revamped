$ErrorActionPreference = "Stop"

# Use Velocity Nexus for legacy Minecraft libraries
$velocityBase = "https://nexus.velocitypowered.com/repository/maven-public"

$libs = @(
    # These worked from Central
    @{ url="https://repo1.maven.org/maven2/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar"; path="libraries/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar" }
    @{ url="https://repo1.maven.org/maven2/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar"; path="libraries/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar" }
    
    # LWJGL from Velocity Nexus
    @{ url="$velocityBase/org/lwjgl/lwjgl/lwjgl/2.9.4-nightly-20150209/lwjgl-2.9.4-nightly-20150209.jar"; path="libraries/org/lwjgl/lwjgl/lwjgl/2.9.4-nightly-20150209/lwjgl-2.9.4-nightly-20150209.jar" }
    @{ url="$velocityBase/org/lwjgl/lwjgl/lwjgl_util/2.9.4-nightly-20150209/lwjgl_util-2.9.4-nightly-20150209.jar"; path="libraries/org/lwjgl/lwjgl/lwjgl_util/2.9.4-nightly-20150209/lwjgl_util-2.9.4-nightly-20150209.jar" }
    @{ url="$velocityBase/org/lwjgl/lwjgl/lwjgl-platform/2.9.4-nightly-20150209/lwjgl-platform-2.9.4-nightly-20150209-natives-windows.jar"; path="libraries/org/lwjgl/lwjgl/lwjgl-platform/2.9.4-nightly-20150209/lwjgl-platform-2.9.4-nightly-20150209.jar" }
    
    # Paulscode from Velocity Nexus
    @{ url="$velocityBase/com/paulscode/codecjorbis/20230120/codecjorbis-20230120.jar"; path="libraries/com/paulscode/codecjorbis/20230120/codecjorbis-20230120.jar" }
    @{ url="$velocityBase/com/paulscode/codecwav/20101023/codecwav-20101023.jar"; path="libraries/com/paulscode/codecwav/20101023/codecwav-20101023.jar" }
    @{ url="$velocityBase/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar"; path="libraries/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar" }
    @{ url="$velocityBase/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar"; path="libraries/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar" }
    @{ url="$velocityBase/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar"; path="libraries/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar" }
    
    # launchwrapper - try Mojang's version on Central
    @{ url="https://repo1.maven.org/maven2/net/minecraft/launchwrapper/1.12/launchwrapper-1.12.jar"; path="libraries/org/mcphackers/launchwrapper/1.3.0/launchwrapper-1.3.0.jar" }
    
    # JSON - try a standard version on Central
    @{ url="https://repo1.maven.org/maven2/org/json/json/20230227/json-20230227.jar"; path="libraries/org/json/json/20230311/json-20230311.jar" }
    
    # ASM - these worked
    @{ url="https://repo1.maven.org/maven2/org/ow2/asm/asm/9.10.1/asm-9.10.1.jar"; path="libraries/org/ow2/asm/asm/9.10.1/asm-9.10.1.jar" }
    @{ url="https://repo1.maven.org/maven2/org/ow2/asm/asm-tree/9.10.1/asm-tree-9.10.1.jar"; path="libraries/org/ow2/asm/asm-tree/9.10.1/asm-tree-9.10.1.jar" }
    @{ url="https://repo1.maven.org/maven2/org/ow2/asm/asm-commons/9.10.1/asm-commons-9.10.1.jar"; path="libraries/org/ow2/asm/asm-commons/9.10.1/asm-commons-9.10.1.jar" }
)

foreach ($lib in $libs) {
    $fullPath = "C:\Users\Nicho\OneDrive\Documentos\minecraft\" + $lib.path
    $dir = Split-Path $fullPath -Parent
    if (-not (Test-Path $dir)) {
        New-Item -ItemType Directory -Path $dir -Force | Out-Null
    }
    Write-Host "Downloading $($lib.path)..."
    try {
        Invoke-WebRequest -Uri $lib.url -OutFile $fullPath -UseBasicParsing -TimeoutSec 60
        Write-Host "  OK"
    }
    catch {
        Write-Host "  FAILED: $($_.Exception.Message)" -ForegroundColor Red
    }
}

# Extract natives from lwjgl-platform
$nativesJar = "C:\Users\Nicho\OneDrive\Documentos\minecraft\libraries\org\lwjgl\lwjgl\lwjgl-platform\2.9.4-nightly-20150209\lwjgl-platform-2.9.4-nightly-20150209.jar"
$nativesDir = "C:\Users\Nicho\OneDrive\Documentos\minecraft\libraries\natives"
if (Test-Path $nativesJar) {
    Write-Host "Extracting natives..."
    if (-not (Test-Path $nativesDir)) { New-Item -ItemType Directory -Path $nativesDir -Force | Out-Null }
    cd $nativesDir
    # Try to find jar command
    $javaHome = $env:JAVA_HOME
    $jarCmd = if ($javaHome) { "$javaHome\bin\jar.exe" } else { "jar.exe" }
    if (Test-Path $jarCmd) {
        & $jarCmd xf $nativesJar 2>$null
    } else {
        & "jar.exe" xf $nativesJar 2>$null
    }
    Write-Host "Natives extracted to $nativesDir"
}

Write-Host "Done!"