# NanoEntropyRandom

[![License: MIT](img.shields.io)](opensource.org)
[![Java-Version](img.shields.io)](adoptium.net)

**NanoEntropyRandom** 是一个基于 Java 21 开发的高强度伪随机数生成工具库。

与传统的 `java.util.Random` 不同，本项目在 **线性同余算法 (LCG)** 的基础上，实时注入了 **纳秒级环境熵 (System.nanoTime)**。这种设计有效解决了高频调用下随机序列易被预测的问题，并提供了更均匀的统计分布表现。

## ✨ 核心特性

- 🛠️ **熵注入机制**：动态混合纳秒级系统时间，增加随机数序列的不可预测性。
- ⚖️ **权重随机系统**：支持自定义权重数组及单体概率判定（已通过 1,000,000 次采样测试）。
- 🔀 **洗牌算法**：内置高效率的数组乱序功能。
- 📝 **自定义字符池**：支持指定字符集生成各种长度的随机字符串。
- 🚀 **零依赖**：仅依赖标准的 JDK 21+，无任何第三方包，轻量且高效。

## 📦 安装 (Maven)

在你的 `pom.xml` 中添加以下配置：

### 1. 添加仓库
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>