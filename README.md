# GlobalWarming 全球变暖

这个 Slimefun 附属插件致力于向游戏添加环境变化机制。

该插件值得尝试，99% 可配置。

## 目录

* [下载](#下载)
* [配置](#配置)
  * [生物群系配置](#生物群系配置)
* [机制](#机制)
  * [环境机制](#环境机制)
  * [污染机制](#污染机制)
  * [新闻系统](#新闻系统)
* [物品与机器](#物品与机器)
* [API](#api)

## 下载

**自动更新**: 汉化版构建#4已包含自动更新功能，默认开启

点击这里下载 GlobalWarming: [下载 GlobalWarming](https://builds.guizhanss.net/GuizhanCraft/GlobalWarming-CN/master)

<p align="center">
  <a href="https://github.com/ybw0014/GlobalWarming-CN/actions/workflows/maven.yml">
    <img src="https://github.com/ybw0014/GlobalWarming-CN/actions/workflows/maven.yml/badge.svg" alt="Java CI"/>
  </a>

  <a href="https://builds.guizhanss.net/ybw0014/GlobalWarming-CN/master">
    <img src="https://builds.guizhanss.net/f/ybw0014/GlobalWarming-CN/master/badge.svg" alt="Build status"/>
  </a>
</p>

## 配置

在成功安装插件后，你可以查看 [config.yml](https://github.com/GuizhanCraft/GlobalWarming-CN/blob/master/src/main/resources/config.yml) 文件来修改配置。
- `worlds` 允许你设置白名单模式下要启用本插件的世界，或黑名单模式下要禁用本插件的世界
- `world-filter-type` 是用来设置世界过滤器的类型 (可用值: ``blacklist``, ``whitelist``)
- 在 `mechanics` 中，你可以自定义可用的环境变化机制
- 在 `pollution` 中，你可以设置造成污染或吸收污染的机器、物品以及实体
- 在 `temperature-options` 中，你可以设置环境温度的计算方式

编辑文件后，请重启服务器使新配置生效!

### 生物群系配置

官方版构建#11，汉化版构建#2对配置文件进行了修改，以兼容MC1.18对生物群系的修改。  
新版本已不再使用`biomes.yml`来配置生物群系。

在`/plugins/GlobalWarming/biome-maps/`目录中，可以看到2个生物群系地图，分别是：

- **pre-1.18.json**: MC1.17及以下版本的生物群系
- **post-1.18.json**: MC1.18及以上版本的生物群系

插件将根据服务器版本自动选择可用的生物群系地图。  
如果生物群系地图配置不正确，将使用默认自带的数据。

你可以参考以下示例来配置生物群系的温度和夜间降温幅度:

<details>
  <summary>生物群系地图配置示例</summary>
 
  ```yaml
   ...
   {
     {
     "value": {
       "temperature": 10,
       "max-temp-drop-at-night": 14
     },
     "biomes": [
       "minecraft:dripstone_caves"
     ]
   },
   {
     "value": {
       "temperature": 19,
       "max-temp-drop-at-night": 10
     },
     "biomes": [
       "minecraft:lush_caves"
     ]
   },
   ...
  ```
 </details>

## 机制

### 环境机制

- 森林大火 (仅在已加载的区块中发生, 火焰方块会在高温地区随机出现在最高方块上)
- 冰川融化 (仅在已加载的区块中发生, 冰块会在高温地区随机融化)
- 玩家减速 (玩家所在地区的温度足够高或足够低时发生)
- 玩家着火 (玩家所在地区的温度非常高时发生)

### 污染机制

不同世界的污染机制可以在配置文件中修改。目前有两种类型的污染机制：

##### 1. 污染产生

- 喂养动物
- 当可产生污染的机器完成一次操作（合成或消耗燃料）
- 当可产生污染的物品在机器中被消耗

##### 2. 污染吸收

- 树苗生长成树
- 当可吸收污染的机器完成一次操作 (默认只有空气压缩机)

### 新闻系统

- 当世界中的污染值改变时，世界中的所有玩家都会受到一条"突发新闻"。这些新闻是从现实世界中挑选的。

## 物品与机器

- 温度计 (显示当前环境温度)
- 空气质量监测仪 (显示当前环境的温度变化)
- 空气压缩机 (可以压缩二氧化碳，并吸收污染)
- 空气罐
- 二氧化碳气罐 (包含压缩二氧化碳)
- 朱砂 (GEO 资源，用于制造水银)
- 水银 (资源，用于制造空气压缩机)
- 过滤器 (用于制造空气压缩机)

![温度计](https://cdn.jsdelivr.net/gh/GuizhanCraft/GlobalWarming-CN@master/images/thermometer.png)

## API

你可以在 [`me.poma123.globalwarming.api`](https://github.com/poma123/GlobalWarming/tree/master/src/main/java/me/poma123/globalwarming/api) 包中找到完整的API文档。
