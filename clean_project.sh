#!/bin/bash

echo "🧹 LIMPIANDO PROYECTO"
echo "==================="

# Eliminar archivos de Mac
find . -name ".DS_Store" -type f -delete
find . -name "._*" -type f -delete

# Eliminar iconos y favicons
find . -name "*.ico" -type f -delete 2>/dev/null
find . -name "favicon.*" -type f -delete 2>/dev/null
find . -name "*.png" -not -path "./target/site/serenity/*" -type f -delete 2>/dev/null
find . -name "*.jpg" -not -path "./target/site/serenity/*" -type f -delete 2>/dev/null

# Eliminar carpetas innecesarias
rm -rf assets/icons/ 2>/dev/null
rm -rf images/ 2>/dev/null  
rm -rf icons/ 2>/dev/null
rm -rf .idea/ 2>/dev/null
rm -rf .vscode/ 2>/dev/null

# Eliminar archivos temporales
find . -name "*.log" -not -path "./target/site/serenity/*" -type f -delete 2>/dev/null
find . -name "*.tmp" -type f -delete 2>/dev/null
find . -name "*.backup" -type f -delete 2>/dev/null
find . -name "*~" -type f -delete 2>/dev/null

echo "✅ LIMPIEZA COMPLETADA"
echo ""
echo "📁 Estructura limpia:"
find . -type f -not -path "./.git/*" -not -path "./target/*" | head -10

echo ""
echo "📊 Archivos relevantes:"
echo "   📂 .java: $(find . -name "*.java" | wc -l)"
echo "   📋 .feature: $(find . -name "*.feature" | wc -l)"
echo "   📄 config: $(find . -name "*.properties" -o -name "*.xml" | wc -l)"
