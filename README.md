                                                                                        Retail Shelf AR Scanner


A real-time Augmented Reality (AR) mobile application built for retail shelf auditing. This application identifies products on a shelf and uses AR overlays to mark them as detected, ensuring a seamless and non-repetitive scanning experience.

🚀 Features & Requirement Mapping
This project fulfills all core functional criteria outlined in the assignment:


Initial Detection: As soon as the camera is pointed at a shelf, the system identifies all visible products and applies a visual indicator.


Continuous Scanning: Uses a high-frequency image analysis pipeline to detect new products in real-time as the user pans the device.


Non-Duplication Logic: Implements spatial tracking to ensure that once a product is marked, it is not re-detected or double-marked if the user pans back to the same area.

Live On-Camera AR: All processing occurs live on the camera's memory buffer. No photos are saved to disk or processed post-capture.

🛠️ Tech Stack
Language: Kotlin

Camera Framework: CameraX (for lifecycle-aware camera preview and image analysis).

ML Engine: Google ML Kit Object Detection & Tracking (Streaming Mode).

Graphics: Custom GraphicOverlay view for hardware-accelerated AR rendering.

🧩 Key Implementation Details
Continuous Analysis
The app utilizes ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST to maintain a smooth UI frame rate while performing heavy ML computations in a background thread executor.

Non-Duplication Algorithm
The GraphicOverlay.kt maintains a mutableSetOf<RectF> of detected product coordinates. For every new frame:

The system maps the ML Kit bounding box to the current screen coordinates.

It uses RectF.intersects to compare new detections against the existing set of "marked" products.

New indicators are only added if they represent a unique, previously unscanned area.

AR Visuals
Bounding Boxes: Cyan borders highlight the precise detection area.

Status Indicators: A green tick ("✓") is centered on each product to signify successful processing.

📋 Prerequisites
Minimum SDK: Android API 24 (or as configured in build.gradle).

Permissions: Camera access is required.

Hardware: A physical Android device is recommended for AR performance testing.

🏃 How to Run
Clone the repository and open it in Android Studio.

Ensure the package name is correctly set to com.infilect.assignment.retailscanner.

Sync Gradle and build the project.

Deploy to a physical device and grant Camera Permissions when prompted.

Point the camera at retail products or boxes to begin scanning.
