// https://github.com/lewin/SPOJ/blob/master/horrible.java
static class SegmentTree {
	        public SegmentTree lc = null, rc = null;
	        public long sum, lazy;
	        public int start, end;
	        
	        public SegmentTree (int start, int end) {
	            this.start = start;
	            this.end = end;
	            if (start != end) {
	                int mid = (start + end) >> 1;
	                lc = new SegmentTree (start, mid);
	                rc = new SegmentTree (mid + 1, end);
	            }
	        }
	        
	        public long range () {
	            return end - start + 1;
	        }
	        
	        public void push () {
	            if (lc != null) {
	                lc.sum += lc.range () * lazy;
	                lc.lazy += lazy;
	                rc.sum += rc.range () * lazy;
	                rc.lazy += lazy;
	                sum = lc.sum + rc.sum;
	            }
	            lazy = 0;
	        }
	        
	        public void update (int b, int e, int val) {
	            if (b > end || start > e) return;
	            if (b == start && e == end) {
	                sum += range () * val;
	                lazy += val;
	                return;
	            }
	            
	            push ();
	            
	            int mid = (start + end) >> 1;
	            if (mid >= e) lc.update (b, e, val);
	            else if (mid < b) rc.update (b, e, val);
	            else {
	                lc.update (b, mid, val);
	                rc.update (mid + 1, e, val);
	            }
	            sum = lc.sum + rc.sum;
	        }
	        
	        public long query (int b, int e) {
	            if (b > end || start > e) return 0;
	            if (b == start && e == end) {
	                return sum;
	            }
	            
	            push ();
	            
	            int mid = (start + end) >> 1;
	            if (mid >= e) return lc.query (b, e);
	            else if (mid < b) return rc.query (b, e);
	            else return lc.query (b, mid) + rc.query (mid + 1, e);
	        }
	    }
	}