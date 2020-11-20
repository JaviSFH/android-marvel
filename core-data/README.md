# Core Data module

This module is an android library (`com.android.library`) which acts as repository for the whole app. It could be 
divided in multiple modules or even include its logic within the corresponding `feature-foo` module. Nonetheless the 
most common scenario would be that multiple `feature-foo` modules require to ask for the same data to the same data 
sources, so with this approach the logic is easily shared across the `feature-foo` modules.

## License

    Copyright 2020 Javier Martínez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


